package com.berkay22demirel.sportsdatasource.service;

import com.berkay22demirel.sportsdatasource.model.MatchScore;
import com.berkay22demirel.sportsdatasource.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataSourceService {

    public List<MatchScore> getScores() {
        List<MatchScore> matchScoreList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.tff.org/Default.aspx?pageID=198").get();
            Element matches = doc.getElementById("ctl00_MPane_m_198_935_ctnr_m_198_935_dtlHaftaninMaclari");
            Elements matchTables = matches.getElementsByTag("table");
            matchTables.stream()
                    .filter(m -> m.attr("id").equals(""))
                    .forEach(m -> {
                        MatchScore matchScore = new MatchScore();
                        Elements tds = m.getElementsByTag("td");
                        Elements dateSpanElements = tds.get(0).getElementsByTag("span");
                        Elements homeTeamSpanElements = tds.get(2).getElementsByTag("span");
                        Elements scoreSpanElements = tds.get(3).getElementsByTag("span");
                        Elements awayTeamSpanElements = tds.get(4).getElementsByTag("span");
                        matchScore.setDate(DateUtil.convertToDate(dateSpanElements.get(0).text() + " " + dateSpanElements.get(1).text(), DateUtil.TFF_DATE_TIME_FORMAT));
                        matchScore.setHomeTeam(homeTeamSpanElements.get(0).text());
                        matchScore.setAwayTeam(awayTeamSpanElements.get(0).text());
                        matchScore.setScore(scoreSpanElements.get(0).text() + "-" + scoreSpanElements.get(1).text());
                        matchScore.setTournament("Türkiye Süper Lig");
                        matchScoreList.add(matchScore);
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return matchScoreList;
        }
        return matchScoreList;
    }
}
