package bonfiglio.scozzari.ing_soft.theatersoftware.excpetion.customException;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidDatesException extends Exception {
    String message;
}
