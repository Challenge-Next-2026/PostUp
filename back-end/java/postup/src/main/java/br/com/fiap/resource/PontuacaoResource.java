package br.com.fiap.resource;

import br.com.fiap.bo.PontuacaoBO;
import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.PontuacaoTO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postup/pontuacao")
public class PontuacaoResource {
    // Atributo
    private PontuacaoBO pontuacaoBO = new PontuacaoBO();

    @GetMapping
    public ResponseEntity<List<PontuacaoTO>> findAll() {
        List<PontuacaoTO> pontuacoes = pontuacaoBO.findAll();
        if (pontuacoes != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(pontuacoes);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idPontuacao}")
    public ResponseEntity<?> findById(@PathVariable int idPontuacao) {
        PontuacaoTO pontuacao = pontuacaoBO.findById(idPontuacao);

        if (pontuacao != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(pontuacao);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pontuação não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PontuacaoTO pontuacao) {
        try {
            PontuacaoTO resultado = pontuacaoBO.save(pontuacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar a pontuação");
        }
    }

    @PutMapping("/{idPontuacao}")
    public ResponseEntity<?> update(@PathVariable int idPontuacao, @RequestBody @Valid PontuacaoTO pontuacao) {
        try {
            PontuacaoTO resultado = pontuacaoBO.update(pontuacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar pontuação");
        }
    }

    @DeleteMapping("/{idPontuacao}")
    public ResponseEntity<String> delete(@PathVariable int idPontuacao) {
        if (pontuacaoBO.delete(idPontuacao)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pontuação deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pontuação não encontrado!");
        }
    }
}
