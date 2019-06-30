package com.trl.users.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trl.users.service.deserializer.CustomerDateDeserializer;
import com.trl.users.service.serializer.CustomerDateSerializer;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class BankDataDTO {

    private Long id;
    private String bankAccountNumber;

    @JsonSerialize(using = CustomerDateSerializer.class)
    @JsonDeserialize(using = CustomerDateDeserializer.class)
    private Date dateOfExpiry;

    private Integer cvi;
    private UserDTO userId;

}
