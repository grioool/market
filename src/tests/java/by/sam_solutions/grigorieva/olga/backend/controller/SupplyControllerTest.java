package by.sam_solutions.grigorieva.olga.backend.controller;

import by.sam_solutions.grigorieva.olga.backend.domain.validation.CustomPattern;
import by.sam_solutions.grigorieva.olga.backend.dto.PurchaseDto;
import by.sam_solutions.grigorieva.olga.backend.dto.StorageDto;
import by.sam_solutions.grigorieva.olga.backend.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SupplyControllerTest extends TestCase {

    public void testGetSupplies() {
    }

    public void testGetSuppliesIds() {
    }

    public void testGetUsersByPage() {
    }

    public void testGetSupply() {
    }

    public void testCreate() {
    }

    public void testUpdate() {
    }

    public void testDelete() {
    }
}