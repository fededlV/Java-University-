package spring_boot_beggining.springBoot.model.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //Is the same thing that @Component
public class StudentService {

    private final StudentRespository studentRespository;

    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }


    public List<Student> getStudents() {
        return studentRespository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRespository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalArgumentException("email taken");
        }
        studentRespository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRespository.existsById(studentId);
        if(!exists) {
            throw new IllegalArgumentException("Student with Id " + studentId + " does not exist");
        }
        studentRespository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRespository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student with Id " + studentId + " does not exist"));
        if (name != null && !name.isEmpty() &&
        !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 &&
        !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRespository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalArgumentException("email taken");
            }
            student.setEmail(email);
        }
    }
}
