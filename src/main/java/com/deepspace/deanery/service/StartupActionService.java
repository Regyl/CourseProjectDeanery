package com.deepspace.deanery.service;

import com.deepspace.deanery.api.DictionaryService;
import com.deepspace.deanery.config.StartupProperties;
import com.deepspace.deanery.exception.DeaneryException;
import com.deepspace.deanery.model.Human;
import com.deepspace.deanery.model.Instruction;
import com.deepspace.deanery.model.InstructionGroup;
import com.deepspace.deanery.model.Student;
import com.deepspace.deanery.model.StudentGroup;
import com.deepspace.deanery.model.dictionary.CathedraDic;
import com.deepspace.deanery.model.dictionary.InstructionBasisDic;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import com.deepspace.deanery.model.dictionary.StudentGroupPrefixDic;
import com.deepspace.deanery.model.dictionary.StudentStatusDic;
import com.deepspace.deanery.model.embedded.FullName;
import com.deepspace.deanery.model.embedded.PassportInfo;
import com.deepspace.deanery.repository.HumanRepository;
import com.deepspace.deanery.repository.InstructionGroupRepository;
import com.deepspace.deanery.repository.InstructionRepository;
import com.deepspace.deanery.repository.StudentGroupRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.deepspace.deanery.RandomUtils.getRandomDictionaryItem;
import static com.deepspace.deanery.RandomUtils.getRandomDictionaryItems;

@Slf4j
//@Scope(scopeName = "PROTOTYPE")
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.startup.install", havingValue = "true")
public class StartupActionService {

    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    private final StartupProperties startupProperties;

    private final DictionaryService dictionaryService;
    private final HumanRepository humanRepository;
    private final StudentRepository studentRepository;
    private final InstructionRepository instructionRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final InstructionGroupRepository instructionGroupRepository;
    
    private List<CathedraDic> cathedraDics;
    private List<InstructionTypeDic> instructionTypeDics;
    private List<StudentStatusDic> studentStatusDics;
    private List<InstructionBasisDic> instructionBasisDics;
    private List<StudentGroupPrefixDic> studentGroupPrefixDics;
    private List<StudentGroup> studentGroups;
    private List<InstructionGroup> instructionGroups;
    private List<Student> students;
    private List<Human> humans;

    @Timed(description = "Startup time")
    @PostConstruct
    public void init() {
        log.info("Startup schema init is enabled");
        dictionaryInit();
        for (StartupProperties.Table table : startupProperties.getTables()) {
            switch (table) {
                case HUMAN -> initHumans();
                case STUDENT -> initStudents();
                case INSTRUCTION -> initInstructions();
                case STUDENT_GROUP -> initStudentGroups();
                case INSTRUCTION_GROUP -> initInstructionGroups();
            }
        }
    }

    /**
     * Инициализация справочников.
     */
    private void dictionaryInit() {
        List<CathedraDic> cathedraDics = Arrays.stream(CathedraDic.Value.values())
                .map(CathedraDic::new)
                .toList();
        this.cathedraDics = cathedraDics;
        dictionaryService.saveAll(cathedraDics);

        List<InstructionTypeDic> instructionTypeDics = Arrays.stream(InstructionTypeDic.Value.values())
                .map(InstructionTypeDic::new)
                .toList();
        this.instructionTypeDics = instructionTypeDics;
        dictionaryService.saveAll(instructionTypeDics);

        List<StudentStatusDic> studentStatusDics = Arrays.stream(StudentStatusDic.Value.values())
                .map(StudentStatusDic::new)
                .toList();
        this.studentStatusDics = studentStatusDics;
        dictionaryService.saveAll(studentStatusDics);

        List<InstructionBasisDic> instructionBasisDics = Arrays.stream(InstructionBasisDic.Value.values())
                .map(InstructionBasisDic::new)
                .toList();
        this.instructionBasisDics = instructionBasisDics;
        dictionaryService.saveAll(instructionBasisDics);

        List<StudentGroupPrefixDic> studentGroupPrefixDics = Arrays.stream(StudentGroupPrefixDic.Value.values())
                .map(StudentGroupPrefixDic::new)
                .toList();
        this.studentGroupPrefixDics = studentGroupPrefixDics;
        dictionaryService.saveAll(studentGroupPrefixDics);
    }

    private void initHumans() {
        log.info("Human init enabled");

        List<Human> humans = new ArrayList<>(startupProperties.getSize());
        for (int i=0; i < startupProperties.getSize(); i++) {
            Name name = FAKER.name();
            FullName fullName = FullName.builder()
                    .firstName(name.firstName())
                    .lastName(name.lastName())
//                    .middleName(FAKER.funnyName().name())
                    .build();
            PassportInfo passportInfo = PassportInfo.builder()
                    .series(String.valueOf(FAKER.number().randomNumber(4, Boolean.TRUE)))
                    .number(String.valueOf(FAKER.number().randomNumber(6, Boolean.TRUE)))
                    .build();
            Human human = Human.builder()
                    .fullName(fullName)
                    .passportInfo(passportInfo)
                    .gradeBookNumber(UUID.randomUUID().toString())
                    .birthDate(LocalDate.now().minusYears(RANDOM.nextInt(60)))
                    .build();
            humans.add(human);
        }
        this.humans = humans;
        humanRepository.saveAll(humans);
    }

    private void initStudentGroups() {
        log.info("Student group table init enabled");
        List<StudentGroup> studentGroups = new ArrayList<>(startupProperties.getSize());
        for (int i=0; i < startupProperties.getGroupSize(); i++) {
            StudentGroup studentGroup = StudentGroup.builder()
                    .cathedra(getRandomDictionaryItem(cathedraDics))
                    .studentGroupPrefix(getRandomDictionaryItem(studentGroupPrefixDics))
                    .number((short) RANDOM.nextInt(100, 200))
                    .course(RANDOM.nextInt(1, 5))
                    .build();
            studentGroups.add(studentGroup);
        }
        this.studentGroups = studentGroups;
        studentGroupRepository.saveAllAndFlush(studentGroups);
    }

    private void initStudents() {
        log.info("Students table init enabled");
        if (studentGroups.isEmpty() || humans.isEmpty()) {
            throw new DeaneryException(DeaneryException.Reason.STARTUP_EXCEPTION, "Отсутствуют требуемые справочники");
        }

        List<Student> students = new ArrayList<>(startupProperties.getSize());
        for (int i=0; i < startupProperties.getSize(); i++) {
            LocalDate eduStart = LocalDate.now().minusYears(RANDOM.nextInt(10));
            Student student = Student.builder()
                    .studentStatus(getRandomDictionaryItem(studentStatusDics))
                    .eduStart(eduStart)
                    .eduEnd(eduStart.plusYears(4))
                    .studentGroup(getRandomDictionaryItem(studentGroups))
                    .human(getRandomDictionaryItem(humans))
                    .build();
            students.add(student);
        }
        this.students = students;
        studentRepository.saveAll(students);
    }

    private void initInstructions() {
        log.info("Instruction table init enabled");
        List<Instruction> instructions = new ArrayList<>(startupProperties.getSize());
        for (int i=0; i<startupProperties.getSize()/5; i++) {
            InstructionTypeDic instType = getRandomDictionaryItem(instructionTypeDics);
            String payload = getInstructionPayload(instType);
            Instruction instruction = Instruction.builder()
                    .number(FAKER.number().digits(9))
                    .instructionBasis(getRandomDictionaryItem(instructionBasisDics))
                    .instructionType(instType)
                    .payload(payload)
                    .students(getRandomDictionaryItems(students, 5))
                    .date(LocalDate.now().minusYears(RANDOM.nextInt(10)))
                    .instructionGroup(getRandomDictionaryItem(instructionGroups))
                    .build();
            instructions.add(instruction);
        }
        instructionRepository.saveAll(instructions);
    }

    private void initInstructionGroups() {
        log.info("Instruction group table init enabled");
        List<InstructionGroup> instructionGroups = new ArrayList<>(startupProperties.getSize());
        for (int i=0; i < startupProperties.getGroupSize(); i++) {
            InstructionGroup instructionGroup = InstructionGroup.builder()
                    .groupNumber(FAKER.number().toString())
                    .build();
            instructionGroups.add(instructionGroup);
        }
        this.instructionGroups = instructionGroups;
        instructionGroupRepository.saveAllAndFlush(instructionGroups);
    }

    private String getInstructionPayload(InstructionTypeDic instrType) {
        return switch (instrType.getValue()) {
            case GROUP_CHANGE ->
                    String.format("Переход из группы %s в группу %s", getRandomDictionaryItem(studentGroups).toString(), getRandomDictionaryItem(studentGroups).toString());
            case APPOINT_STUDENT_GROUP ->
                    String.format("Зачисление в группу %s", getRandomDictionaryItem(studentGroups).toString());
            default -> FAKER.harryPotter().book();
        };
    }

}
