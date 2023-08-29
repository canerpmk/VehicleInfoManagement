package com.example.sahibinden.service.impl;

import com.example.sahibinden.Document.DocumentProvider;
import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.repository.MarkaRepository;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParseServiceTest {
    @Mock
    private MarkaServiceImpl markaService;
    @Mock
    private MarkaRepository markaRepository;

    @Mock
    private ModelServiceImpl modelService;

    @Mock
    private KasaServiceImpl kasaService;

    @Mock
    private MotorServiceImpl motorService;

    //FIXME use Impl
    @Mock
    private OzellikServiceImpl ozellikService;
    @Mock
    private DocumentProvider documentProviderMock;

    @InjectMocks
    private ParseServiceImpl parseService;


//    @Test
//    void updateMarkas() {
//        List<Marka> mockMarkaList = Arrays.asList(new Marka(), new Marka());
//        when(markaService.addMarkas(any())).thenReturn(mockMarkaList);
//
//        List<Marka> expected = Arrays.asList(new Marka(), new Marka());
//
//
//        List<Marka> actual = parseService.updateMarkas();
//
//        assertEquals(expected, actual);
//        verify(markaService, times(1)).addMarkas(mockMarkaList);
//    }


    @Test
    void parseMarkaPage() {

    }

    @Test
    void parseModelPage() {
    }

    @Test
    void updateKasas() {
    }

    @Test
    void parseKasaPage() {
    }

    @Test
    void parseKasaaPage() {
    }

    @Test
    void updateMotors() {
    }

    @Test
    void parseMotorPage() {
    }

    @Test
    void parseMotorrPage() {
    }

    @Test
    public void testParseOzellikPage() throws IOException {
        Document documentMock = mock(Document.class);

        when(documentProviderMock.getDocument(anyString())).thenReturn(documentMock);

        List<Ozellik> result = parseService.parseOzellikPage();

        assertNotNull(result);

        verify(documentProviderMock, times(1)).getDocument(anyString());
    }

}