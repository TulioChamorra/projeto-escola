package io.github.tuliochamorra.rest;

import io.github.tuliochamorra.model.entity.Team;
import io.github.tuliochamorra.model.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/class")
@CrossOrigin("http://localhost:4200")
public class TeamController {

    private TeamRepository repository;

    @Autowired
    public TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Team> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team salvar(@RequestBody @Valid Team team){
        return repository.save(team);
    }

    @GetMapping("{id}")
    public Team acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id)
                .map(team -> {
                    repository.delete(team);
                    return Void.TYPE;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Team teamAtualizado){
        repository.findById(id)
                .map(team -> {
                    teamAtualizado.setId(team.getId());
                    return repository.save(teamAtualizado);
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
    }
}
