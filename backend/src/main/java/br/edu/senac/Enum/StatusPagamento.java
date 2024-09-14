package br.edu.senac.Enum;

import br.edu.senac.Exceptions.ErrorResponseException;
import org.springframework.http.HttpStatus;

public enum StatusPagamento {
    PENDENTE(1),
    APROVADO(2),
    PEDIDO_FEITO(3),
    PEDIDO_ENTREGUE(4),
    CANCELADO(5);

    private int code;

    StatusPagamento(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static StatusPagamento valueOf(int code) {
        for(StatusPagamento value : StatusPagamento.values()){
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new ErrorResponseException(HttpStatus.NOT_FOUND, "Tipo pagamento não encontrado.");
    }
}
