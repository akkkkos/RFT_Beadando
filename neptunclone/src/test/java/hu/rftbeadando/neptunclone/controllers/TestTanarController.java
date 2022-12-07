package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.controllers.web.TanarController;
import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.formmodels.TantargyFormValues;
import hu.rftbeadando.neptunclone.services.tanar.TanarServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
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

import java.util.Collection;

@ExtendWith(MockitoExtension.class)
public class TestTanarController {

    private TanarController underTest;

    @Mock
    private TanarServiceInterface tanarService;

    @Mock
    private TantargyServiceInterface tantargyService;

    @Mock
    private TantargyFormValues tantargyFormValues;

    @Mock
    private Model model;

    @Mock
    private Collection<TantargyEntity> tantargyak;

    @Mock
    private TanarEntity tanarEntity = new TanarEntity("name", "username");

    private Long id = Long.valueOf(1);

    private Long tantargyId = Long.valueOf(2);

    @BeforeEach
    public void setUp() {
        underTest = new TanarController(tanarService, tantargyService);
    }

    @Test
    public void testTanarPage() {
        //given
        given(tantargyService.getAllTantargyByTanarId(id)).willReturn(tantargyak);
        given(tanarService.getTanarById(id)).willReturn(tanarEntity);

        //when
        String result = underTest.tanarPage(id, model);

        //then
        verify(tantargyService).getAllTantargyByTanarId(id);
        verify(tanarService).getTanarById(id);
        verify(model).addAttribute("tantargyak", tantargyak);
        verify(model).addAttribute("id", id);
        verify(model).addAttribute("tanar", tanarEntity);
        assertEquals(result, "tanar");
    }

    @Test
    public void testAddTantargyPage() {
        //given

        //when
        String result = underTest.addTantargyPage(id, model);

        //then
        verify(model).addAttribute("id", id);
        assertEquals(result, "addTantargy");
    }

    @Test
    public void testAddTantargyToDbShouldGiveBackAddTantargyMissingUrl() {
        //given
        given(tantargyFormValues.getName()).willReturn("");

        //when
        RedirectView redirectView = underTest.addTantargyToDb(tantargyFormValues, id);

        //then
        verify(tantargyFormValues).getName();
        assertEquals(redirectView.getUrl(), "/tanar/1/addTantargy?missing=1");
    }

    @Test
    public void testAddTantargyToDbShouldGiveBackCorrectUrlWhenTantargyExists() {
        //given
        given(tantargyFormValues.getName()).willReturn("name");
        given(tantargyFormValues.getStartTime()).willReturn("start");
        given(tantargyFormValues.getDayOfTheWeek()).willReturn("day");

        //when
        RedirectView redirectView = underTest.addTantargyToDb(tantargyFormValues, id);

        //then
        verify(tantargyFormValues, atLeast(2)).getName();
        verify(tantargyFormValues, atLeast(2)).getDayOfTheWeek();
        verify(tantargyFormValues, atLeast(2)).getStartTime();
        verify(tantargyFormValues).getDurationInMinutes();
        verify(tantargyFormValues).getMaxHallgato();
        verify(tantargyFormValues).getKredit();
        verify(tanarService).getTanarById(id);
        assertEquals(redirectView.getUrl(), "/tanar/1");
    }

    @Test
    public void testRemoveTantargy() {
        //given

        //when
        RedirectView redirectView = underTest.removeTantargy(id, tantargyId);

        //then
        verify(tantargyService).deleteByIdOnlyTantargy(tantargyId);
        assertEquals(redirectView.getUrl(), "/tanar/1?removed=1");
    }
}
