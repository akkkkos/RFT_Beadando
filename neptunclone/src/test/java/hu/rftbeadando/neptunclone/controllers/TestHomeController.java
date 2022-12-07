package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.controllers.web.HomeController;
import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.formmodels.LoginFormValues;
import hu.rftbeadando.neptunclone.formmodels.RegisterFormValues;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tanar.TanarServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestHomeController {

    private HomeController underTest;

    @Mock
    private HallgatoServiceInterface hallgatoService;

    @Mock
    private TanarServiceInterface tanarService;

    @Mock
    private Model model;

    @Mock
    private LoginFormValues loginFormValues;

    @Mock
    private RegisterFormValues registerFormValues;

    @Mock
    private TanarEntity tanar= new TanarEntity("name", "username");

    private final Long idNull = null;

    private final Long id = Long.valueOf(1);

    private final int badLogin = 1;

    private final int exists = 2;

    private final int missing = 3;

    @BeforeEach
    public void setUp() {
        underTest = new HomeController(hallgatoService, tanarService);
    }

    @Test
    public void testLoginPage() {
        //given

        //when
        String result = underTest.loginPage(badLogin, model);

        //then
        verify(model).addAttribute("badLogin", badLogin);
        assertEquals(result, "login");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackBadLoginUrlWhenTanarAndTanarIdIsNull() {
        //given
        given(loginFormValues.getType()).willReturn("tanar");
        given(loginFormValues.getUsername()).willReturn("username");
        given(tanarService.getTanarIdByUsername("username")).willReturn(idNull);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginFormValues);

        //then
        verify(loginFormValues).getType();
        verify(loginFormValues).getUsername();
        verify(tanarService).getTanarIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/?badLogin=1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackCorrectUrlWhenTanarAndTanarIdIsNotNull() {
        //given
        given(loginFormValues.getType()).willReturn("tanar");
        given(loginFormValues.getUsername()).willReturn("username");
        given(tanarService.getTanarIdByUsername("username")).willReturn(id);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginFormValues);

        //then
        verify(loginFormValues).getType();
        verify(loginFormValues).getUsername();
        verify(tanarService).getTanarIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/tanar/1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackBadLoginUrlWhenNotTanarAndHallgatoIdIsNull() {
        //given
        given(loginFormValues.getType()).willReturn("nemtanar");
        given(loginFormValues.getUsername()).willReturn("username");
        given(hallgatoService.getHallgatoIdByUsername("username")).willReturn(idNull);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginFormValues);

        //then
        verify(loginFormValues).getType();
        verify(loginFormValues).getUsername();
        verify(hallgatoService).getHallgatoIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/?badLogin=1");
    }

    @Test
    public void testLoginPageWithPostShouldGiveBackCorrectUrlWhenNotTanarAndHallgatoIdIsNotNull() {
        //given
        given(loginFormValues.getType()).willReturn("nemtanar");
        given(loginFormValues.getUsername()).willReturn("username");
        given(hallgatoService.getHallgatoIdByUsername("username")).willReturn(id);

        //when
        RedirectView redirectView = underTest.loginPageWithPost(loginFormValues);

        //then
        verify(loginFormValues).getType();
        verify(loginFormValues).getUsername();
        verify(hallgatoService).getHallgatoIdByUsername("username");
        assertEquals(redirectView.getUrl(), "/hallgato/1");
    }

    @Test
    public void testRegister() {
        //given

        //when
        String result = underTest.register(missing, exists, model);

        //then
        verify(model).addAttribute("exists", exists);
        verify(model).addAttribute("missing", missing);
        assertEquals(result, "register");
    }

    @Test
    public void testRegisterUserToDbShouldGiveRegisterMissingUrl() {
        //given
        given(registerFormValues.getName()).willReturn("");

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerFormValues);

        //then
        verify(registerFormValues).getName();
        assertEquals(redirectView.getUrl(), "/register?missing=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterSuccessUrlWhenTanarNotExists() {
        //given
        given(registerFormValues.getName()).willReturn("name");
        given(registerFormValues.getUsername()).willReturn("username");
        given(registerFormValues.getType()).willReturn("tanar");
        given(tanarService.existsTanarByUserName("username")).willReturn(false);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerFormValues);

        //then
        verify(registerFormValues, atLeast(2)).getName();
        verify(registerFormValues, atLeast(2)).getUsername();
        verify(registerFormValues).getType();
        verify(tanarService).existsTanarByUserName("username");
        assertEquals(redirectView.getUrl(), "/?registerSuccess=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterExistsUrlWhenTanarExists() {
        //given
        given(registerFormValues.getName()).willReturn("name");
        given(registerFormValues.getUsername()).willReturn("username");
        given(registerFormValues.getType()).willReturn("tanar");
        given(tanarService.existsTanarByUserName("username")).willReturn(true);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerFormValues);

        //then
        verify(registerFormValues).getName();
        verify(registerFormValues, atLeast(2)).getUsername();
        verify(registerFormValues).getType();
        verify(tanarService).existsTanarByUserName("username");
        assertEquals(redirectView.getUrl(), "/register?exists=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterSuccessUrlWhenHallgatoNotExists() {
        //given
        given(registerFormValues.getName()).willReturn("name");
        given(registerFormValues.getUsername()).willReturn("username");
        given(registerFormValues.getType()).willReturn("nemtanar");
        given(hallgatoService.existsHallgatoByUserName("username")).willReturn(false);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerFormValues);

        //then
        verify(registerFormValues, atLeast(2)).getName();
        verify(registerFormValues, atLeast(2)).getUsername();
        verify(registerFormValues).getType();
        verify(hallgatoService).existsHallgatoByUserName("username");
        assertEquals(redirectView.getUrl(), "/?registerSuccess=1");
    }

    @Test
    public void testRegisterUserToDBShouldGiveBackRegisterExistsUrlWhenHallgatoExists() {
        //given
        given(registerFormValues.getName()).willReturn("name");
        given(registerFormValues.getUsername()).willReturn("username");
        given(registerFormValues.getType()).willReturn("nemtanar");
        given(hallgatoService.existsHallgatoByUserName("username")).willReturn(true);

        //when
        RedirectView redirectView = underTest.registerUserToDb(registerFormValues);

        //then
        verify(registerFormValues).getName();
        verify(registerFormValues, atLeast(2)).getUsername();
        verify(registerFormValues).getType();
        verify(hallgatoService).existsHallgatoByUserName("username");
        assertEquals(redirectView.getUrl(), "/register?exists=1");
    }
}
