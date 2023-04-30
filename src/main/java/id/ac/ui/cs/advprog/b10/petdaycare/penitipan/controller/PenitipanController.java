package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.HewanRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.hewan.HewanService;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service.penitipan.PenitipanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Penitipan")
@RequiredArgsConstructor
public class PenitipanController {
    private final PenitipanService penitipanService;

    // TODO : Lengkapi Controller
}

