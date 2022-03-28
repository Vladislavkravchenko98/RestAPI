package com.example.rest.controllers;

import com.example.rest.models.FileUploadUtil;
import com.example.rest.models.Student;
import com.example.rest.models.Subject;
import com.example.rest.models.Timer;
import com.example.rest.repository.StudentRepository;
import com.example.rest.repository.SubjectRepository;
import com.example.rest.dto.ResponseStatusPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    //////////////////////////////////////////////////////////////////////////////////////////////////Работа с предметом
    //Кладем предмет
    @PostMapping("/addSubject")
    public ResponseEntity<Subject> addItem(@RequestBody Subject subject){
        if(subject == null){
            return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
        } else{
            subjectRepository.save(subject);
            return new ResponseEntity<Subject>(subject, HttpStatus.OK);
        }
    }
    //Получаем список всех предметов
    @GetMapping("/listOfSubject")
    public List<Subject> listOfItems(){
        return subjectRepository.findAll();
    }
    //Заменяем предмет
    @PutMapping("/replacement/{id}")
    public ResponseEntity<Subject> replacement(@RequestBody Subject subject,
                                               @PathVariable Subject id){
        if(subject == null){
            return new ResponseEntity<Subject>(HttpStatus.BAD_REQUEST);
        } else {
            id.setActive(subject.getActive());
            id.setNameOfSubject(subject.getNameOfsubject());
            subjectRepository.save(id);

            return new ResponseEntity<Subject>(id, HttpStatus.OK);
        }
    }
    //Удаляем предмет
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Subject> delete(@PathVariable String id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {

            Subject s = subjectRepository.getById(Long.parseLong(id));

            subjectRepository.delete(s);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////Работа со студентом
    //Кладем студента
    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        if(student == null){
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        } else{
            studentRepository.save(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
    }
    //Получаем список всех студентов
    @GetMapping("/listOfStudents")
    public List<Student> lististOfStudents(){
        List<Student> s = studentRepository.findAll();
        return s;
    }
    //Заменяем студента
    @PutMapping("/studentReplacement/{id}")
    public ResponseEntity<Student> studentReplacement(@RequestBody Student student,
                                                      @PathVariable Student id){
        if(student == null){
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        } else {
            id.setName(student.getName());
            id.setVisit(student.getVisit());
            studentRepository.save(id);

            return new ResponseEntity<Student>(id, HttpStatus.OK);
        }
    }
    //Удаляем студента
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id){
        if(id == null){
            return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
        } else {

            Student s = studentRepository.getById(Long.parseLong(id));

            studentRepository.delete(s);

            return new ResponseEntity<Student>(HttpStatus.OK);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////Работа с картинками (загрузка файла)
    @PostMapping("/uploadFile/{id}")
    public RedirectView saveImage(@RequestParam("image") MultipartFile multipartFile,
                                  @PathVariable String id) throws IOException {
        Student student = studentRepository.getById(Long.parseLong(id));

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        student.setPhotos(fileName);

        Student savedUser = studentRepository.save(student);

        String uploadDir = "user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/users", true);
    }

    @PutMapping("/editStutusById/{id}")
    public ResponseEntity<ResponseStatusPair> getId(@PathVariable(name = "id") String id, //В заголовке
                                                    @RequestParam(name ="status") String status) throws InterruptedException {
        Student student = studentRepository.getById(Long.parseLong(id));
        ResponseStatusPair resp = new ResponseStatusPair();
        resp.setId(Long.parseLong(id));
        resp.setOldStatus(student.isStatus());
        resp.setNewStatus(Boolean.parseBoolean(status));

        student.setStatus(Boolean.parseBoolean(status));

        Timer t = new Timer();
        t.Timer5();

        studentRepository.save(student);

        return new ResponseEntity<ResponseStatusPair>(resp, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Student>> status(@RequestParam(name ="online", required = false) Boolean online,
                                                @RequestParam(name ="id", required = false) String id){
        if(online == null && id == null){
            List<Student> students = studentRepository.findAll();
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }else if(online == null && id != null){
           List<Student> students = studentRepository.findAllByIdAfter(Long.parseLong(id));
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        } else if(online != null && id == null){
            List<Student> students = studentRepository.findByStatus(online);
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }else{
            List<Student> students = studentRepository.findByStatusAndAndId(online, Long.parseLong(id));
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }
    }
}


