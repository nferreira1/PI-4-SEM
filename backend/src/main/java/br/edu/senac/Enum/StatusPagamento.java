package br.edu.senac.Enum;

import br.edu.senac.Exceptions.ErrorResponseException;
import org.springframework.http.HttpStatus;

public enum StatusPagamento {
    PENDENTE,
    APROVADO,
    PEDIDO_FEITO,
    PEDIDO_ENTREGUE,
    CANCELADO;
}
