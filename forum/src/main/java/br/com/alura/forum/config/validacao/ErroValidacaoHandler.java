package br.com.alura.forum.config.validacao;

import br.com.alura.forum.config.dtovalidacao.ErroDeFornularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice       // Classe responsavel por tratar exceções de validação de formilario
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code= HttpStatus.BAD_REQUEST)                  //Lançar exceção '400, BAD REQUEST'
    @ExceptionHandler(MethodArgumentNotValidException.class)      // Anotação que diz pro spring que esse metodo deve ser chamado dentro de algum controller e o tipo de exceção tratar quando houver, ou seja, qualquer exceção di tipo passado dentro do parametro da 'ANOTATION' em algumn RestControler, esse metodo sera chamado.
    public List<ErroDeFornularioDto> handle(MethodArgumentNotValidException ex){
        List<ErroDeFornularioDto> dto = new ArrayList<>();

       List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
       fieldErrors.forEach(e->{
           String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
           ErroDeFornularioDto erro = new ErroDeFornularioDto(e.getField(), mensagem);
           dto.add(erro);
       });

       return dto;
    }
        //Esse metodo pegara todas exception do tipo 'MethodArgumentNotValidException' e me trara os campos de informação de erro desejada, nesse caso usei a classe 'ErroDeFornularioDto' para trazer apenas o campo que contem o erro e o erro.
}
