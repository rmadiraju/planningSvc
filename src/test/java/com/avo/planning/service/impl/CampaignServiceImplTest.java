package com.avo.planning.service.impl;

import com.avo.planning.PlanningSvcApp;
import com.avo.planning.domain.Campaign;
import com.avo.planning.repository.CampaignRepository;
import com.avo.planning.service.CampaignService;
import com.avo.planning.web.rest.CampaignResource;
import com.avo.planning.web.rest.LogsResource;
import com.avo.planning.web.rest.TestUtil;
import com.avo.planning.web.rest.errors.ExceptionTranslator;
import org.junit.*;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlanningSvcApp.class)
public class CampaignServiceImplTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";


    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final LocalDate DEFAULT_CREATED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate UPDATED_CREATED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_START_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault()).plusDays(5);

    private static final LocalDate DEFAULT_END_DATE = DEFAULT_START_DATE.plusDays(5);
    private static final LocalDate UPDATED_END_DATE = DEFAULT_END_DATE.plusDays(5);

    private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImplTest.class);


    private Campaign campaign;

    @Mock
    private CampaignService campaignServiceMock;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignRepository campaignRepository;

    private MockMvc restCampaignMockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        final CampaignResource campaignResource = new CampaignResource(campaignService);
        this.restCampaignMockMvc = MockMvcBuilders.standaloneSetup(campaignResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(TestUtil.createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        campaign = createEntity();
    }


    @After
    public void tearDown() {
    }

    @Test
    public void findAll() throws Exception {

        restCampaignMockMvc.perform(get("/api/campaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void testMandatoryIsTemplate() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();
        campaign.setTemplate(null);
        // Initialize the database
        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isBadRequest());

        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);

    }

    class CreateCampaignTask implements Callable<String> {

        private Campaign _campaign = createEntity();

        public CreateCampaignTask(String name) {
            this._campaign.setName(name);
        }

        @Override
        public String call() throws Exception {
            campaignService.save(_campaign);
            return _campaign.getId();
        }
    }

    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        logger.info(String.format("Test %s %s, spent %d microseconds",
            testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
    }


    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description) {
            logInfo(description, "succeeded", nanos);
        }

        @Override
        protected void failed(long nanos, Throwable e, Description description) {
            logInfo(description, "failed", nanos);
        }

        @Override
        protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
            logInfo(description, "skipped", nanos);
        }

        @Override
        protected void finished(long nanos, Description description) {
            logInfo(description, "finished", nanos);
        }
    };

    @Test
    public void create1000CampaignsThreaded() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//            new ThreadPoolExecutor(13, 15, 500L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());

        List<Future<String>> resultList = new ArrayList<>();

        for ( int i = 0 ; i <= 1000; i++ ) {
            Future<String> result = executorService.submit(new CreateCampaignTask("test" + i));
            resultList.add(result);
        }

        int x = 0;
        for ( Future<String> f : resultList) {
            try {
                String res  = f.get();
                System.out.println(x++ + ":" + res + "\n");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    @Test
    public void createSingleCampaign() throws Exception {

        campaign.setTemplate(false);

        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaign)))
            .andExpect(status().isCreated());
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", campaign.getId()))
            .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingCampaign() throws Exception {
        // Get the design
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void findOne() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void findCampaignsByCalendar() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void getCampaignTemplates() {
    }

    @Test
    public void findByPeriod() {
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createEntity() {
        Campaign campaign = new Campaign()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .active(DEFAULT_ACTIVE)
            .template(true)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);

        return campaign;
    }


}
