package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.LoginRequest;
import br.com.fiap.to.UsuarioTO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/postup/usuario")
public class UsuarioResource {
    // Atributo
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GetMapping
    public ResponseEntity<List<UsuarioTO>> findAll() {
        List<UsuarioTO> usuarios = usuarioBO.findAll();
        if (usuarios != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable int idUsuario) {
        UsuarioTO usuario = usuarioBO.findById(idUsuario);

        if (usuario != null) {
            // mensagem de retorno OK
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } else {
            // mensagem de retorno Error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioTO usuario, HttpSession sessao) {
        try {
            UsuarioTO resultado = usuarioBO.save(usuario);
            // Se o objeto for nulo
            if (resultado == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado ou não foi possível cadastrar o usuário.");
            // Se não
            } else {
                // Armazenamento de idUsuario na sessão
                sessao.setAttribute("idUsuario", usuario.getIdUsuario());
                return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
            }
        // Erro de SQL
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao cadastrar o usuário.");
        // Erro de dados inválidos
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession sessao) {
        // Encerra sessão
        sessao.invalidate();
        return ResponseEntity.ok("Logout realizado com sucesso!\nVolte sempre!");
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> update(@PathVariable int idUsuario, @RequestBody @Valid UsuarioTO usuario, HttpSession sessao) {
        // Armazena o id do usuário logado na sessão
        Integer idUsuarioLogado = (Integer) sessao.getAttribute("idUsuario");
        // Se esse id for nulo o login não foi feito
        if (idUsuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Faça login para editar seus dados.");
        }
        // Se o id for diferente do usuário impede ele de editar a conta de outro usuário
        if (idUsuarioLogado != idUsuario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para editar esta conta.");
        }
        try {
            // Atribui id ao objeto usuario
            usuario.setIdUsuario(idUsuarioLogado);
            // Chama o updateByOwner()
            UsuarioTO resultado = usuarioBO.update(usuario, idUsuarioLogado);
            // Se o resultado for diferente de null, deu certo
            if (resultado != null) {
                return ResponseEntity.ok(resultado);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        } catch (Exception e) {
            // exibe o erro no output
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar usuário.");
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> delete(@PathVariable int idUsuario) {
        if (usuarioBO.delete(idUsuario)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest credenciais, HttpSession sessao) {
        try {
            UsuarioTO usuario = usuarioBO.login(credenciais.getEmail(), credenciais.getSenha());
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
            }
            // Armazenamento de idUsuario na sessão
            sessao.setAttribute("idUsuario", usuario.getIdUsuario());
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao realizar login.");
        }
    }
}
