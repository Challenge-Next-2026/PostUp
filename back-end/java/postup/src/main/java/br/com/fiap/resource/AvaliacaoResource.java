package br.com.fiap.resource;

import br.com.fiap.bo.AvaliacaoBO;
import br.com.fiap.to.AvaliacaoTO;
import br.com.fiap.to.AvaliarPostagemRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/postup/avaliacao")
public class AvaliacaoResource {
    // Atributo
    private AvaliacaoBO avaliacaoBO = new AvaliacaoBO();

    @GetMapping
    public ResponseEntity<List<AvaliacaoTO>> findAll() {
        List<AvaliacaoTO> avaliacoes = avaliacaoBO.findAll();
        if (avaliacoes != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(avaliacoes);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idAvaliacao}")
    public ResponseEntity<?> findById(@PathVariable int idAvaliacao) {
        AvaliacaoTO avaliacao = avaliacaoBO.findById(idAvaliacao);

        if (avaliacao != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(avaliacao);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AvaliacaoTO avaliacao) {
        try {
            AvaliacaoTO resultado = avaliacaoBO.save(avaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar a avaliação");
        }
    }

    @PutMapping("/{idAvaliacao}")
    public ResponseEntity<?> update(@PathVariable int idAvaliacao, @RequestBody @Valid AvaliacaoTO avaliacao) {
        try {
            AvaliacaoTO resultado = avaliacaoBO.update(avaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar avaliação");
        }
    }

    @DeleteMapping("/{idAvaliacao}")
    public ResponseEntity<String> delete(@PathVariable int idAvaliacao) {
        if (avaliacaoBO.delete(idAvaliacao)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Avaliação deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Avaliação não encontrada!");
        }
    }

    @PostMapping("/postagem")
    public ResponseEntity<?> avaliarPostagem(@RequestBody @Valid AvaliarPostagemRequest request, HttpSession session) {
        // Recupera o usuário autenticado na sessão
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        // Se esse usuario for nulo, não tem login
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }
        try {
            // chamada de metodo com objeto
            AvaliacaoTO resultado = avaliacaoBO.avaliarPostagem(request.getIdPostagem(), idUsuario);
            // Se o metodo retornar nulo, deu erro
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Postagem inexistente, não pertence ao usuário ou já possui avaliação.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a avaliação.");
        }
    }
}
