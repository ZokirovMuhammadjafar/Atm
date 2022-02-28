package Jafar.ibook.models.personal;

import Jafar.ibook.models.Auditable;
import lombok.*;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"serial", "number"}, callSuper = false)
public class Passport extends Auditable
{
    private String serial;
    private String number;
    private String ownerId;
    private String fullName;
}
