package co.istad.ite2mbanking.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BasedErrorResponse {
    private  BasedError error;

}
