package Jafar.ibook.models;

import Jafar.ibook.utils.BaseUtils;
import lombok.*;


import java.util.Date;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auditable implements BaseEntity {
    private String id = BaseUtils.genID();
    private String createdBy;
    private Date createdAt = new Date();
    private String updatedBy;
    private Date updatedAt;
    private int deleted;
}
