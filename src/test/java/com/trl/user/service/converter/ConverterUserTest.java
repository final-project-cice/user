package com.trl.user.service.converter;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.repository.entity.UserEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ConverterUserTest {

    private UserDTO expected_UserDTO;
    private UserEntity expected_UserEntity;

    private Set<UserDTO> expected_UserDTOSet;
    private Set<UserEntity> expected_UserEntitySet;

    @Before
    public void setUp() throws Exception {

        expected_UserEntity = new UserEntity()
                .setId(1L)
                .setFirstName("First Name User_1")
                .setLastName("Last Name User_1")
                .setEmail("user_1@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1));

        expected_UserDTO = new UserDTO()
                .setId(1L)
                .setFirstName("First Name User_1")
                .setLastName("Last Name User_1")
                .setEmail("user_1@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1));

        expected_UserDTOSet = new HashSet<>();
        expected_UserDTOSet.add(new UserDTO()
                .setId(1L)
                .setFirstName("First Name User_1")
                .setLastName("Last Name User_1")
                .setEmail("user_1@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1)));
        expected_UserDTOSet.add(new UserDTO()
                .setId(2L)
                .setFirstName("First Name User_2")
                .setLastName("Last Name User_2")
                .setEmail("user_2@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.FEBRUARY, 2)));

        expected_UserEntitySet = new HashSet<>();
        expected_UserEntitySet.add(new UserEntity()
                .setId(1L)
                .setFirstName("First Name User_1")
                .setLastName("Last Name User_1")
                .setEmail("user_1@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 1)));
        expected_UserEntitySet.add(new UserEntity()
                .setId(2L)
                .setFirstName("First Name User_2")
                .setLastName("Last Name User_2")
                .setEmail("user_2@email.com")
                .setPassword("strong_password")
                .setBirthday(LocalDate.of(1970, Month.FEBRUARY, 2)));

    }

    // =================================================================================================================
    // ================================================================================================ mapEntityToDTO()
    // =================================================================================================================
    @Test
    public void mapEntityToDTO() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(expected_UserEntity);
        Assert.assertEquals(expected_UserDTO, actual_UserDTO);
    }

    @Test
    public void mapEntityToDTO_Parameter_Null() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(null);
        Assert.assertNull(actual_UserDTO);
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Id() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setId(2L));
        assertNotEquals(expected_UserDTO.getId(), actual_UserDTO.getId());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_FirstName() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setFirstName("First Name User_1***"));
        assertNotEquals(expected_UserDTO.getFirstName(), actual_UserDTO.getFirstName());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_LastName() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setLastName("Last Name User_1***"));
        assertNotEquals(expected_UserDTO.getLastName(), actual_UserDTO.getLastName());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Email() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setEmail("user_1@email.com***"));
        assertNotEquals(expected_UserDTO.getEmail(), actual_UserDTO.getEmail());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Password() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setPassword("strong_password***"));
        assertNotEquals(expected_UserDTO.getPassword(), actual_UserDTO.getPassword());
    }

    @Test
    public void mapEntityToDTO_Incorrect_Value_Birthday() {
        UserDTO actual_UserDTO = ConverterUser.mapEntityToDTO(new UserEntity()
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 2)));
        assertNotEquals(expected_UserDTO.getBirthday(), actual_UserDTO.getBirthday());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetEntityToSetDTO()
    // =================================================================================================================
    @Test
    public void mapSetEntityToSetDTO() {
        Set<UserDTO> actual_UserDTOSet = ConverterUser.mapSetEntityToSetDTO(expected_UserEntitySet);
        assertEquals(expected_UserDTOSet, actual_UserDTOSet);
    }

    @Test
    public void mapSetEntityToSetDTO_Parameter_Null() {
        Set<UserDTO> actual_UserDTOSet = ConverterUser.mapSetEntityToSetDTO(null);
        assertNull(actual_UserDTOSet);
    }

    // =================================================================================================================
    // ================================================================================================ mapDTOToEntity()
    // =================================================================================================================
    @Test
    public void mapDTOToEntity() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(expected_UserDTO);
        assertEquals(expected_UserEntity, actual_UserEntity);
    }

    @Test
    public void mapDTOToEntity_Parameter_Null() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(null);
        assertNull(actual_UserEntity);
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Id() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setId(2L));
        assertNotEquals(expected_UserEntity.getId(), actual_UserEntity.getId());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_FirstName() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setFirstName("First Name User_1***"));
        assertNotEquals(expected_UserEntity.getFirstName(), actual_UserEntity.getFirstName());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_LastName() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setLastName("Last Name User_1***"));
        assertNotEquals(expected_UserEntity.getLastName(), actual_UserEntity.getLastName());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Email() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setEmail("user_1@email.com***"));
        assertNotEquals(expected_UserEntity.getEmail(), actual_UserEntity.getEmail());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Password() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setPassword("strong_password***"));
        assertNotEquals(expected_UserEntity.getPassword(), actual_UserEntity.getPassword());
    }

    @Test
    public void mapDTOToEntity_Incorrect_Value_Birthday() {
        UserEntity actual_UserEntity = ConverterUser.mapDTOToEntity(new UserDTO()
                .setBirthday(LocalDate.of(1970, Month.JANUARY, 2)));
        assertNotEquals(expected_UserEntity.getBirthday(), actual_UserEntity.getBirthday());
    }

    // =================================================================================================================
    // ========================================================================================== mapSetDTOToSetEntity()
    // =================================================================================================================
    @Test
    public void mapSetDTOToSetEntity() {
        Set<UserEntity> actual_UserEntitySet = ConverterUser.mapSetDTOToSetEntity(expected_UserDTOSet);
        assertEquals(expected_UserEntitySet, actual_UserEntitySet);
    }

    @Test
    public void mapSetDTOToSetEntity_Parameter_Null() {
        Set<UserEntity> actual_UserEntitySet = ConverterUser.mapSetDTOToSetEntity(null);
        assertNull(actual_UserEntitySet);

    }

}