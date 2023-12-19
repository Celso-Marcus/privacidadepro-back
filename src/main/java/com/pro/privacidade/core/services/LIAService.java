package com.pro.privacidade.core.services;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import com.pro.privacidade.core.entities.LIA;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.http.dtos.InventoryDTO;
import com.pro.privacidade.infra.http.dtos.LIADTO;
import com.pro.privacidade.infra.repositories.InventoryLegitimateInterestRepository;
import com.pro.privacidade.infra.repositories.LIARepository;
import org.apache.commons.text.StringSubstitutor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LIAService {

    private final LIARepository liaRepository;
    private final InventoryLegitimateInterestRepository inventoryLegitimateInterestRepository;
    private final ModelMapper modelMapper;

    public LIAService(InventoryLegitimateInterestRepository inventoryLegitimateInterestRepository, ModelMapper modelMapper, LIARepository liaRepository) {
        this.inventoryLegitimateInterestRepository = inventoryLegitimateInterestRepository;
        this.modelMapper = modelMapper;
        this.liaRepository = liaRepository;
    }

    public List<LIADTO> getAll() {
        return liaRepository
                .findAll()
                .stream()
                .map(lia -> modelMapper.map(lia, LIADTO.class))
                .toList();
    }

    public LIADTO findById(Long id) {
        return liaRepository
                .findById(id)
                .map(lia -> modelMapper.map(lia, LIADTO.class))
                .orElseThrow(() -> new ResourceNotFound("LIA", id));
    }

    public LIADTO create(LIADTO liaDTO) {
        var creationTime = LocalDateTime.now();
        liaDTO.setCreatedAt(creationTime);
        liaDTO.setUpdatedAt(creationTime);
        return modelMapper.map(liaRepository.save(modelMapper.map(liaDTO, LIA.class)), LIADTO.class);
    }

    public void delete(Long id) {
        var lia = this.liaRepository.findById(id).orElseThrow(() -> new ResourceNotFound("LIA", id));
        this.liaRepository.delete(lia);
    }

    public LIADTO update(Long id,LIADTO liaDTO) {
        var lia = this.liaRepository.findById(id).orElseThrow(() -> new ResourceNotFound("LIA", id));

        lia.setAnswers(liaDTO.getAnswers());
        lia.setDpoName(liaDTO.getDpoName());
        lia.setInventoryName(liaDTO.getInventoryName());
        lia.setJustification(liaDTO.getJustification());
        lia.setUpdatedAt(LocalDateTime.now());

        return modelMapper.map(liaRepository.save(lia), LIADTO.class);
    }

    public List<InventoryDTO> getAllLegitimateInterestInventories() {
        return inventoryLegitimateInterestRepository
                .getAllLegitimateInterest()
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .toList();
    }

    private FontProvider fontProvider() throws IOException {
        String regularFontPath = "./fonts/OpenSans-Regular.ttf";
        String boldFontPath = "./fonts/OpenSans-Bold.ttf";
        FontProgram regularFontProgram = null;
        FontProgram boldFontProgram = null;
        try {
            regularFontProgram = FontProgramFactory.createFont(regularFontPath);
            boldFontProgram = FontProgramFactory.createFont(boldFontPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FontProvider provider = new DefaultFontProvider();
        provider.addFont(regularFontProgram);
        provider.addFont(boldFontProgram);
        return provider;
    }

    private String readFileToString(Path file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
