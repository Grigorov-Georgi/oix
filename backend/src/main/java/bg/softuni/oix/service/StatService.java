package bg.softuni.oix.service;
import bg.softuni.oix.repository.OfferRepository;
import bg.softuni.oix.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class StatService {
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    private Logger LOGGER = LoggerFactory.getLogger(StatService.class);

    public StatService(UserRepository userRepository, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }


//    @Scheduled(cron = "0/15 * * * * *")
    @Scheduled(cron = "0 55 23 * * *")
    public void getStatistics() throws IOException {

        LOGGER.info("Scheduled Task Executed");
        String fileName = System.currentTimeMillis() + "_daily_report.txt";
        File file = new File("C:/oix/backend/src/main/resources/statistics/" + fileName);
        try {
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(this.getInfo());
            myWriter.close();
        } catch (IOException e) {
            LOGGER.error("File with such name already exists!");
        }
    }

    public String getInfo() {
        long users = userRepository.count();

        long offers = offerRepository.count();
        long offersForSale = offerRepository.findAllByBuyerIsNull().size();
        long soldOffers = offerRepository.findAllByBuyerIsNotNull().size();
        long todayOffers = offerRepository.findAllByReleaseDateAfter(LocalDate.now().minusDays(1)).size();


        StringBuilder sb = new StringBuilder();
        sb.append("Statistics:\n")
                .append("Total users count: ")
                .append(users)
                .append("\n")
                .append("Total offers count: ")
                .append(offers)
                .append("\n")
                .append("Total number of offers for sale: ")
                .append(offersForSale)
                .append("\n")
                .append("Total number of sold offers: ")
                .append(soldOffers)
                .append("\n")
                .append("Total number of offers uploaded today: ")
                .append(todayOffers);

        return sb.toString();
    }
}
