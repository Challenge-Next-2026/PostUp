package br.com.fiap.resource;

import br.com.fiap.bo.PostagemBO;
import br.com.fiap.to.PostagemTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("postup/postagem")
public class PostagemResource {
    // Atributo
    private PostagemBO postagemBO = new PostagemBO();

    @GetMapping
    public ResponseEntity<List<PostagemTO>> findAll() {
        List<PostagemTO> postagens = postagemBO.findAll();
        if (postagens != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(postagens);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idPostagem}")
    public ResponseEntity<?> findById(@PathVariable int idPostagem) {
        PostagemTO postagem = postagemBO.findById(idPostagem);

        if (postagem != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(postagem);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Postagem não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid PostagemTO postagem, HttpSession sessao) {
        // Pega o idUsuario da sessão já iniciada (do usuario já logado) e converte para Integer
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Se o id for nulo
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para criar uma postagem.");
        }
        // Se o id existir
        try {
            postagem.setDataPostagem(LocalDate.now());
            PostagemTO resultado = postagemBO.savePostWithUser(postagem, idUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar a postagem: " + e.getMessage());
        }
    }

    @PutMapping("/{idPostagem}")
    public ResponseEntity<?> update(@PathVariable int idPostagem, @RequestBody @Valid PostagemTO postagem, HttpSession sessao) {
        // Pega o idUsuario da sessão iniciada
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Verifica se esse idUsuario não veio nulo
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Faça login para editar uma postagem.");
        }
        try {
            postagem.setIdPostagem(idPostagem);
            boolean atualizado = postagemBO.updateByOwner(postagem, idUsuario);
            if (atualizado) {
                return ResponseEntity.ok(postagem);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para editar essa postagem.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar postagem");
        }
    }

    @DeleteMapping("/{idPostagem}")
    public ResponseEntity<String> delete(@PathVariable int idPostagem, HttpSession sessao) {
        // Pega o idUsuario da sessão iniciada
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Verifica se esse idUsuario não veio nulo
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para excluir uma postagem.");
        }
        try {
            boolean excluido = postagemBO.deleteByOwner(idPostagem, idUsuario);
            if (excluido) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para excluir esta postagem.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir a postagem.");
        }
    }
}
