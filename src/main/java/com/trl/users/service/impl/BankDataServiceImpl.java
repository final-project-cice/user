package com.trl.users.service.impl;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.UserIdIsNullException;
import com.trl.users.exceptions.UserIsNullException;
import com.trl.users.exceptions.UserNotHaveBankDataException;
import com.trl.users.exceptions.UserWithIdNotExistException;
import com.trl.users.repository.BankDataRepository;
import com.trl.users.repository.UserRepository;
import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.BankDataService;
import com.trl.users.service.converter.ConverterBankData;
import com.trl.users.service.converter.ConverterUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class BankDataServiceImpl implements BankDataService {

    private final BankDataRepository bankDataRepository;
    private final UserRepository userRepository;

    /**
     * @param bankDataDTO
     * @return
     */
    @Override
    public BankDataDTO create(BankDataDTO bankDataDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException {

        BankDataDTO bankDataResult = null;

        log.debug("************ create() ---> idUser = " + bankDataDTO.getUser() + " ---> bankDataDTO = " + bankDataDTO);

        // TODO: Nose si es necesario comprobar de null todos parametros que se van utilizar en meethodo. Es que se compica mucho la lectura ce mtodo.

        if (bankDataDTO.getUser() != null) {

            if (bankDataDTO.getUser().getId() != null && bankDataDTO.getUser().getId() != 0) {

                Optional<UserEntity> userFromRepositoryById = userRepository.findById(bankDataDTO.getUser().getId());

                if (userFromRepositoryById.isPresent()) {

                    BankDataEntity savedBankData = bankDataRepository.save(ConverterBankData.mapDTOToEntity(bankDataDTO));

                    log.debug("************ create() ---> savedBankData = " + savedBankData);

                    bankDataResult = ConverterBankData.mapEntityToDTO(savedBankData);

                } else {
                    throw new UserWithIdNotExistException("User with this id = '" + bankDataDTO.getUser().getId() + "' not exist.");
                }
            } else {
                throw new UserIdIsNullException("The parameter 'user' that is passed, contains value 'userId'. Value 'userId' equal NULL or ZERO. Not allowed parameters NULL or ZERO.");
            }
        } else {
            throw new UserIsNullException("The parameter 'user' that is passed, equal NULL. Not allowed parameter NULL.");
        }

        log.debug("************ create() ---> bankDataResult = " + bankDataResult);

        return bankDataResult;
    }

    @Override
    public Set<BankDataDTO> findByUser(UserDTO userDTO) {

        Set<BankDataDTO> bankDataSetResult = null;

        log.debug("************ findByUser() ---> userDTO = " + userDTO);

        Set<BankDataEntity> usersFromRepositoryById = bankDataRepository.findByUser(ConverterUser.mapDTOToEntity(userDTO));

        log.debug("************ findByUser() ---> usersFromRepositoryById = " + usersFromRepositoryById);

        bankDataSetResult = ConverterBankData.mapSetEntityToSetDTO(usersFromRepositoryById);

        log.debug("************ findByUser() ---> bankDataSetResult  = " + bankDataSetResult);

        return bankDataSetResult;
    }

    /**
     * @param userDTO
     * @return
     * @throws UserNotHaveBankDataException
     */
    @Transactional
    @Override
    public Boolean deleteByUser(UserDTO userDTO) throws UserNotHaveBankDataException {

        boolean isDeletedBankData = false;

        log.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        Set<BankDataEntity> bankDataFromRepositoryByUser = bankDataRepository.findByUser(userEntity);

        log.debug("************ deleteByUser() ---> bankDataFromRepositoryByUser = " + bankDataFromRepositoryByUser);

        if (!bankDataFromRepositoryByUser.isEmpty()) {
            bankDataRepository.deleteByUser(userEntity);
            isDeletedBankData = true;
        } else {
            log.debug("************ deleteByUser() ---> this user = '" + userDTO + "' not have bankData");
            throw new UserNotHaveBankDataException("This user = '" + userDTO + "' not have bankData");
        }

        log.debug("************ deleteByUser() ---> isDeletedBankData = " + isDeletedBankData);

        return isDeletedBankData;
    }

}