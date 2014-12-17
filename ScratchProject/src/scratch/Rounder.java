package scratch;

import java.util.ArrayList;
import java.util.List;

public class Rounder {
	
	public static void main(String[] args)
	{	
		int[] coords;
		
		coords = new int[]{680,2,634,64,642,84,598,88,552,136,576,168,564,210,538,262,668,240,722,180,768,174,794,156,858,184,860,156,910,134,968,166,952,70,922,38,920,0,780,0};
		printCoords(coords);
		coords = new int[]{970,166,1014,172,1046,174,1084,190,1104,184,1146,182,1156,216,1156,260,1138,310,1140,346,1122,340,1112,310,1092,306,1080,278,1070,312,1078,360,1070,402,1038,400,1030,416,1008,412,974,382,962,344,956,298,932,256,892,242,850,182,900,128,958,156};
		printCoords(coords);
		coords = new int[]{236,240,266,220,304,270,326,234,356,220,380,220,404,246,418,280,430,274,402,226,444,262,462,260,494,252,532,244,606,256,662,240,726,174,758,164,788,150,848,172,890,240,932,254,954,300,974,384,964,406,984,426,968,430,988,444,926,432,906,408,882,402,852,380,776,470,740,472,700,576,710,606,660,662,694,722,606,740,506,754,482,728,402,748,432,704,502,542,456,486,402,474,382,398,340,382,288,402,270,378,236,362,282,354,276,302,284,274,228,236};
		printCoords(coords);
		coords = new int[]{140,634,158,486,174,452,214,452,238,460,260,470,260,444,250,444,262,428,262,410,248,400,284,404,350,388,380,400,402,470,440,480,464,502,496,532,502,546,454,652,420,692,436,700,412,716,382,758,344,744,350,700,326,748,288,762,240,760,242,738,210,712,214,672,190,610,208,580,192,580,180,608,140,632};
		printCoords(coords);
		coords = new int[]{1018,508,1002,474,960,464,968,438,922,434,894,404,844,378,768,476,732,474,696,586,708,616,656,666,696,724,746,696,740,682,768,718,774,754,760,798,790,832,836,840,866,812,880,778,910,776,944,718,938,700,894,702,890,678,846,652,852,628,836,610,868,588,872,558,894,530,920,502,980,516,1010,500};
		printCoords(coords);
		coords = new int[]{1086,658,1052,664,942,628,950,662,936,706,884,704,884,680,832,650,862,588,888,530,926,496,978,522,1004,542,1030,570,1036,596,1028,620,1046,634,1080,652};
		printCoords(coords);	
	}
	
	public static void printCoords(int[] coords)
	{
		List<Double> dblCoords = new ArrayList<>();
		{
			for (int value: coords)
			{
				value = (value / 10) * 10;
				double dbl = value;
				dblCoords.add(dbl);
				System.out.print(dbl + ",");
			}
			System.out.println();
			System.out.println("***************************************************");
		}
	}

}
