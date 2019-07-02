package com.trl.users.controller;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserIsNull;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.service.BankDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/user/bankData")
public class BankDataResource {

    private final BankDataService bankDataService;

    /**
     *
     */
    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody BankDataDTO bankData) {
        ResponseEntity response = null;

        log.debug("************ create() ---> bankData = " + bankData);

        BankDataDTO resultService = null;

        // TODO: Anotacion para todo catch. No estoy seguro que HttpStaus es mejor usarlo.
        try {
            resultService = bankDataService.create(bankData);
        } catch (ExceptionUserWithIdNotExist exceptionUserWithIdNotExist) {
            log.error("************ create() ---> user with this id = '" + bankData.getUser().getId() + "' not exist.", exceptionUserWithIdNotExist);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserWithIdNotExist.getMessage());
        } catch (ExceptionUserIdIsNull exceptionUserIdIsNull) {
            log.error("************ create() ---> bankData not have assign value user >>> 'userId'. 'userId' = '" + bankData.getUser().getId() + "'. Variable 'userId' not allowed assign values NULL or ZERO.", exceptionUserIdIsNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserIdIsNull.getMessage());
        } catch (ExceptionUserIsNull exceptionUserIsNull) {
            log.error("************ create() ---> bankData not have assign value to the user. User = '" + bankData.getUser() + "'.", exceptionUserIsNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserIsNull.getMessage());
        }

        log.debug("************ create() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ create() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/findByUser")
    public ResponseEntity<Set<BankDataDTO>> findByUser(@RequestBody UserDTO user) {
        ResponseEntity<Set<BankDataDTO>> response = null;

        log.debug("************ findByUser() ---> userId = " + user);

        Set<BankDataDTO> resultService = null;

        resultService = bankDataService.findByUser(user);

        log.debug("************ findByUser() ---> resultService = " + resultService);

        response = (!resultService.isEmpty()) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByUser() ---> response = " + response);

        return response;
    }

}