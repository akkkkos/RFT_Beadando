package hu.rftbeadando.neptunclone.controllers;

import hu.rftbeadando.neptunclone.entities.HallgatoEntity;
import hu.rftbeadando.neptunclone.entities.TanarEntity;
import hu.rftbeadando.neptunclone.entities.TantargyEntity;
import hu.rftbeadando.neptunclone.services.hallgato.HallgatoServiceInterface;
import hu.rftbeadando.neptunclone.services.tantargy.TantargyServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestHallgatoController {

    private HallgatoController underTest;

    private Long id = Long.valueOf(1);

    private Long tantargyId = Long.valueOf(1);

    @Mock
    private Model model;

    @Mock
    private HallgatoServiceInterface hallgatoService;

    @Mock
    private TantargyServiceInterface tantargyService;

    private HallgatoEntity hallgato = new HallgatoEntity("name", "username");

    @Mock
    private Collection<TantargyEntity> tantargyak;

    private TanarEntity tanar = new TanarEntity("name", "username");

    @Mock
    private TantargyEntity tantargy = new TantargyEntity("name", "hetfo", "1200", 45, 20, 3,tanar );


    @BeforeEach
    public void setUp() {
        underTest = new HallgatoController(hallgatoService, tantargyService);
    }

    @Test
    public void testHallgatoPage() {
        //given
        given(hallgatoService.getHallgatoById(id)).willReturn(hallgato);
        given(tantargyService.getAllTantargyThatHasHallgatoId(hallgato.hallgatoId)).willReturn(tantargyak);

        //when
        String result = underTest.hallgatoPage(id, model);

        //then
        verify(hallgatoService).getHallgatoById(id);
        verify(tantargyService).getAllTantargyThatHasHallgatoId(hallgato.hallgatoId);
        verify(model).addAttribute("hallgato", hallgato);
        verify(model).addAttribute("tantargyak", tantargyak);
        assertEquals(result, "hallgato");
    }

    @Test
    public void testEnrollTantargyPage() {
        //given
        given(tantargyService.getAllTantargyThatDoesNotHaveHallgatoId(id)).willReturn(tantargyak);

        //when
        String result = underTest.enrollTantargyPage(id, model);

        //then
        verify(tantargyService).getAllTantargyThatDoesNotHaveHallgatoId(id);
        verify(model).addAttribute("id", id);
        verify(model).addAttribute("tantargyak", tantargyak);
        assertEquals("enrollTantargy", result);
    }

    @Test
    public void testEnrollTantargy() {
        //given
        given(hallgatoService.getHallgatoById(id)).willReturn(hallgato);
        given(tantargyService.getTantargyByTantargyId(tantargyId)).willReturn(tantargy);

        //when
        RedirectView redirectView = underTest.enrollTantargy(id, tantargyId);

        //then
        verify(hallgatoService).getHallgatoById(id);
        verify(tantargy).addHallgato(hallgato);
        verify(tantargyService).addTantargy(tantargy);
        assertEquals(redirectView.getUrl(), "/hallgato/{id}/enrollTantargy");

    }

    @Test
    public void testLeaveTantargy() {
        //given
        given(tantargyService.getTantargyByTantargyId(tantargyId)).willReturn(tantargy);

        //when
        RedirectView redirectView = underTest.leaveTantargy(id, tantargyId);

        //then
        verify(tantargyService).getTantargyByTantargyId(tantargyId);
        verify(tantargy).removeHallgato(id);
        verify(tantargyService).addTantargy(tantargy);
        assertEquals(redirectView.getUrl(), "/hallgato/{id}");
    }
}
