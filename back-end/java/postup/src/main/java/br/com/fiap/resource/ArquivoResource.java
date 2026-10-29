package br.com.fiap.resource;

import br.com.fiap.bo.ArquivoBO;
import br.com.fiap.to.ArquivoTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/postup/arquivo")
public class ArquivoResource {
    // Atributo
    private ArquivoBO arquivoBO = new ArquivoBO();

    @GetMapping
    public ResponseEntity<List<ArquivoTO>> findAll() {
        List<ArquivoTO> arquivos = arquivoBO.findAll();
        if (arquivos != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(arquivos);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idArquivo}")
    public ResponseEntity<?> findById(@PathVariable int idArquivo) {
        ArquivoTO arquivo = arquivoBO.findById(idArquivo);

        if (arquivo != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(arquivo);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ArquivoTO arquivo, HttpSession sessao) {
        // Recuperando usuário logado na sessão
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Verificação se o idUsuario da sessão não é nulo
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para cadastrar um arquivo");
        }
        try {
            // Salvando o arquivo
            ArquivoTO arquivoSalvo = arquivoBO.saveByOwner(arquivo, idUsuario);
            // Se o arquivo for nulo, tem algo errado
            if (arquivoSalvo == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A postagem não existe, não pertence ao usuário ou já possui um arquivo.");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(arquivoSalvo);
        } catch (SQLException e) {
            // exibe o erro no output
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno ao salvar o arquivo.");
        }
    }


    @PutMapping("/{idArquivo}")
    public ResponseEntity<?> update(@PathVariable int idArquivo, @RequestBody @Valid ArquivoTO arquivo, HttpSession sessao) {
        // Recuperar o id do usuário logado, sessão em andamento
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Se esse id for nulo, o usuário não está logado
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para atualizar um arquivo.");
        }
        // Garante que o ID da request seja o ID do arquivo atualizado
        arquivo.setIdArquivo(idArquivo);
        try {
            // Chamada de metodo
            ArquivoTO resultado = arquivoBO.updateByOwner(arquivo, idUsuario);
            // Se o objeto vier nulo, algo deu erro
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Arquivo não encontrado ou não pertence a uma postagem sua.");
            } else {
                return ResponseEntity.ok(resultado);
            }
        } catch (SQLException e) {
            // Print de erro no console
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar o arquivo.");
        }
    }


    @DeleteMapping("/{idArquivo}")
    public ResponseEntity<?> delete(@PathVariable int idArquivo, HttpSession sessao) {
        // Recuperar o id do usuário logado, sessão em andamento
        Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
        // Se esse id for nulo, o usuário não está logado
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para excluir um arquivo.");
        }
        try {
            // Chamada de função
            boolean excluido = arquivoBO.deleteByOwner(idArquivo, idUsuario);
            // Se o retorno for false, dá erro
            if (!excluido) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Arquivo não encontrado, não pertence a uma postagem sua ou ainda está associado a outra postagem.");
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (SQLException e) {
            // Print de erro no console
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao excluir o arquivo.");
        }
    }
}
