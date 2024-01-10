package com.pro.privacidade.core.services;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import com.pro.privacidade.infra.http.dtos.RipdDTO;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {

    private final InventoryService inventoryService;
    private final LIAService liaService;
    private final ResourceLoader resourceLoader;

    public PdfService(InventoryService inventoryService, ResourceLoader resourceLoader, LIAService liaService) {
        this.inventoryService = inventoryService;
        this.resourceLoader = resourceLoader;
        this.liaService = liaService;
    }

    public byte[] getLIAPdf(Long id) {
        try {
            var lia = this.liaService.findById(id);
            Map<String, Object> parameters = new HashMap<>();

            String htmlString = getHtmlFileString("lia");

            String[] answers = lia.getAnswers().split(",");
            for (int i = 0; i < answers.length; i++) {
                parameters.put("" + (i + 1), answers[i]);
            }
            parameters.put("dpoName", lia.getDpoName());
            parameters.put("inventoryName", lia.getInventoryName());
            parameters.put("justification", lia.getJustification());

            return generatePdf(htmlString, parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getInventoryPdf(Long id) {
        try {
            var inventory = inventoryService.findById(id);

            String htmlString = getHtmlFileString("inventory");

            Map<String, Object> parameters = convertUsingReflection(inventory);
            parameters.replace("operators", String.join(", ", inventory.getOperators()));
            parameters.replace("systemNames", String.join(", ", inventory.getSystemNames()));
            parameters.replace("underAgeData", inventory.getUnderAgeData() ? "Sim" : "Não");

            return generatePdf(htmlString, parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getRipdPdf(RipdDTO ripdDTO) {
        try {
            var inventory = inventoryService.findById(ripdDTO.getInventoryId());

            String htmlString = getHtmlFileString("ripd");

            Map<String, Object> parameters = convertUsingReflection(inventory);
            parameters.replace("operators", String.join(", ", inventory.getOperators()));
            parameters.replace("systemNames", String.join(", ", inventory.getSystemNames()));
            parameters.replace("underAgeData", inventory.getUnderAgeData() ? "Sim" : "Não");
            parameters.put("ownerName", ripdDTO.getOwnerName());
            parameters.put("ownerEmail", ripdDTO.getOwnerEmail());
            parameters.put("ownerPhone", ripdDTO.getOwnerPhone());
            parameters.put("principleRisk", ripdDTO.getPrincipleRisk());
            parameters.put("userRisk", ripdDTO.getUserRisk());
            parameters.put("dataRisk", ripdDTO.getDataRisk());
            parameters.put("reactionRisk", ripdDTO.getReactionRisk());
            parameters.replace("reasonData", inventory.getReasonData().getName());
            parameters.replace("createdAt", LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"))
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            return generatePdf(htmlString, parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] generatePdf(String htmlString, Map<String, Object> parameters) throws IOException {
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        StringSubstitutor substitutor = new StringSubstitutor(parameters);

        htmlString = substitutor.replace(htmlString);

        PdfWriter writer = new PdfWriter(target);
        PdfDocument pdf = new PdfDocument(writer);
        ConverterProperties props = new ConverterProperties();
        props.setFontProvider(fontProvider());

        try {
            HtmlConverter.convertToPdf(htmlString, pdf, props);
            System.out.println("PDF criado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return target.toByteArray();
    }

    private String getHtmlFileString(String fileName) {
        Resource resource = resourceLoader.getResource("classpath:html/" + fileName + ".html");
        try {
            return readFileToString(resource.getFile().toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> convertUsingReflection(Object object) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }

        return map;
    }
}
