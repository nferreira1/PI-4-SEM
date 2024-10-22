package br.edu.senac.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Essa anotação é utilizada para disparar a validação dos parâmetros do método
 * antes da execução do mesmo. Ela garante que os parâmetros sejam validados
 * antes que qualquer lógica no método
 * anotado seja executada.
 * <p>
 * Quando essa anotação é aplicada a um método, os parâmetros desse método
 * são validados por meio de um Aspecto personalizado que utiliza o Validator.
 * Se alguma violação de restrição for encontrada, a execução é interrompida e
 * uma {@link jakarta.validation.ConstraintViolationException} é lançada.
 * </p>
 *
 * <h3>Exemplo de Uso:</h3>
 *
 * <pre>
 * {@code
 *  @ValidateBeforeExecutionAnnotation
 *  public void meuMetodo(MinhaEntidade entidade) {
 *      // A validação será realizada antes de executar a lógica do método
 *  }
 * }
 * </pre>
 *
 * <h3>Como Funciona:</h3>
 * <ul>
 *   <li>Os parâmetros do método anotado são validados antes da execução.</li>
 *   <li>Se houver erros de validação, o método não será executado e uma exceção será lançada.</li>
 *   <li>A lógica do método só será executada se todas as validações forem bem-sucedidas.</li>
 * </ul>
 *
 * @see jakarta.validation.Validator
 * @see jakarta.validation.ConstraintViolationException
 * @see org.aspectj.lang.annotation.Aspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateBeforeExecutionAnnotation {

}
