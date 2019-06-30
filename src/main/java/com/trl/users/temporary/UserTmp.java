package com.trl.users.temporary;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trl.users.service.deserializer.CustomerDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserTmp {

    private Long id;

    @JsonDeserialize(using = CustomerDateDeserializer.class)
    private Date birthday;

}
