package fr.ghisswill.agenda.security;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private PasswordEncoder encoder;
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        User user = new User(null, "test@ghiss.fr", encoder.encode("pass"), Role.USER);
//        userRepository.save(user);
//    }
//
//    @Test
//    void login_shouldReturnToken() throws Exception {
//        String body = "{'email':'test@ghiss.fr','password':'pass_test'}";
//
//        mockMvc.perform(post("/api/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").exists());
//    }
//
//    @Test
//    void protectedEndpoint_requiresJwt() throws Exception {
//        mockMvc.perform(get("/api/agenda"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void accessProtectedEndpoint_withValidJwt() throws Exception {
//        User user = userRepository.findByEmail("test@ghiss.fr").get();
//        String token = jwtService.generateToken(user);
//
//        mockMvc.perform(get("/api/agenda")
//                .header("Authorization", "Bearer "+ token))
//                .andExpect(status().isOk());
//    }

}
