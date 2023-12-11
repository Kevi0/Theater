package bonfiglio.scozzari.ing_soft.theatersoftware.excpetion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private LocalDateTime timestamp;
    private Integer status;
    private String error, message, path;

    public ErrorMessage(int value, String name, String message) {
    }
}
