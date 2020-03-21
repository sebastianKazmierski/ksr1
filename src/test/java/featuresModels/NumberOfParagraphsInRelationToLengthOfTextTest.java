package featuresModels;

import data.Article;
import grouping.Place;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfParagraphsInRelationToLengthOfTextTest {

    @Test
    void extract() {
        String content = "It said the government and private exporters shipped 26,272\n" +
                "and 46,715 tonnes respectively. Private exporters concluded\n" +
                "advance weekly sales for 106,640 tonnes against 98,152 the\n" +
                "previous week. The said it ministry expects at least 65,000\n" +
                "tonnes in exports next week.\n" +
                "    Thailand has shipped 3.43 mln tonnes of rice in the year to\n" +
                "date, down from 3.68 mln a year ago. It has commitments to\n" +
                "export another 388,390 tonnes this year.'\n" +
                "place=[thailand]}, all.Article{content='Chase Corp Ltd <CHCA.WE> said it will\n" +
                "make an offer for all fully-paid shares and options of\n" +
                "<Entregrowth International Ltd> it does not already own.\n" +
                "    Chase, a property investment firm, said it holds 48 pct of\n" +
                "Entregrowth, its vehicle for expansion in North America.\n" +
                "    It said agreements are being concluded to give it a\n" +
                "beneficial 72.4 pct interest.\n" +
                "    The offer for the remaining shares is one Chase share for\n" +
                "every three Entregrowth shares and one Chase option for every\n" +
                "four Entregrowth options. Chase shares closed on Friday at 4.41\n" +
                "dlrs and the options at 2.38.\n" +
                "    Entregrowth closed at 1.35 dlrs and options at 55 cents.\n" +
                "    Chase said the offer for the remaining 27.6 pct of\n" +
                "Entregrowth, worth 34.2 mln dlrs, involved the issue of 5.80\n" +
                "mln Chase shares and 3.10 mln Chase options.\n" +
                "    Chase chairman Colin Reynolds said the takeover would allow\n" +
                "Entregrowth to concentrate on North American operations with\n" +
                "access to Chase's international funding base and a stronger\n" +
                "executive team. He said there also would be benefits from\n" +
                "integrating New Zealand investment activities.\n" +
                "    Chase said the offer is conditional it receiving accptances\n" +
                "for at least 90 pct of the shares and options.'\n" +
                "place=[new-zealand]}, all.Article{content='The Japan/India-Pakistan-Gulf/Japan\n" +
                "shipping conference said it would cut the extra risk insurance\n" +
                "surcharges on shipments to Iranian and Iraqi ports to a minimum\n" +
                "three pct from 4.5 pct on October 25.\n" +
                "    It said surcharges on shipments of all break-bulk cargoes\n" +
                "to non-Iraqi Arab ports would be reduced to 3.0 pct from 4.5.\n" +
                "    A conference spokesman declined to say why the move was\n" +
                "taken at a time of heightened tension in the Gulf.";
        Article<Place> article = new Article<>(content, Place.UK);
        FeatureExtractor<Place> featureExtractor = new NumberOfParagraphsInRelationToLengthOfText<Place>(new LengthOfText<>());
        FeatureExtractor<Place> lengthOfText = new LengthOfText<>();
        assertEquals(11/lengthOfText.extract(article),featureExtractor.extract(article));
    }
}