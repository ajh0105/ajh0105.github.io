package com.cheongdo.tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 정적 서브페이지 라우팅 (투어, 축제, 숙박, 여행정보, 청도소개)
 */
@Controller
public class SubPageController {

    // ── 청도관광 ────────────────────────
    @GetMapping("/tour/9gyeong")   public String tour9()    { return "tour/sub21"; }
    @GetMapping("/tour/theme")     public String tourTheme() { return "tour/sub22"; }
    @GetMapping("/tour/course")    public String tourCourse(){ return "tour/sub23"; }

    // ── 축제/행사 ────────────────────────
    @GetMapping("/festival/regular")  public String festRegular() { return "festival/sub31"; }
    @GetMapping("/festival/event")    public String festEvent()   { return "festival/sub32"; }

    // ── 숙박/맛집 ────────────────────────
    @GetMapping("/stay/accommodation") public String stayAcc()   { return "stay/sub41"; }
    @GetMapping("/stay/restaurant")    public String stayRest()  { return "stay/sub42"; }
    @GetMapping("/stay/shopping")      public String stayShopping(){ return "stay/sub43"; }

    // ── 여행정보 ────────────────────────
    @GetMapping("/info/transport") public String infoTransport(){ return "info/sub51"; }
    @GetMapping("/info/weather")   public String infoWeather()  { return "info/sub52"; }
    @GetMapping("/info/map")       public String infoMap()      { return "info/sub53"; }

    // ── 청도소개 ────────────────────────
    @GetMapping("/intro/story")   public String introStory()  { return "intro/sub11"; }
    @GetMapping("/intro/history") public String introHistory(){ return "intro/sub12"; }
    @GetMapping("/intro/people")  public String introPeople() { return "intro/sub13"; }
}
