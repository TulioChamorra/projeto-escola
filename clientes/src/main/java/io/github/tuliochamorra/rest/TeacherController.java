package io.github.tuliochamorra.rest;
import io.github.tuliochamorra.model.entity.Teacher;
import io.github.tuliochamorra.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin("http://localhost:4200")
public class TeacherController {

    private TeacherRepository repository;

    @Autowired
    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Teacher> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher salvar(@RequestBody @Valid Teacher teacher){
        return repository.save(teacher);
    }

    @GetMapping("{id}")
    public Teacher acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(teacher -> {
                    repository.delete(teacher);
                    return Void.TYPE;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Teacher teacherAtualizado){
        repository.findById(id)
                .map(teacher -> {
                    teacherAtualizado.setId(teacher.getId());
                    return repository.save(teacherAtualizado);
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
    }
}
