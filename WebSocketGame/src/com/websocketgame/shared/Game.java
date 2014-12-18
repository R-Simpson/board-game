package com.websocketgame.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public enum Game {
	INSTANCE;

	/*
	private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(0, "GREY", new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}, new int[]{2,4}), 
			new Land(1, "GREY", new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 90.0,10.0, 100.0,0.0}, new int[]{1,3,4}), 
			new Land(2, "GREY", new Double[]{100.0,0.0, 90.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}, new int[]{2,5}),
			new Land(3, "GREY", new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}, new int[]{1,2,5,6}), 
			new Land(4, "GREY", new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}, new int[]{3,4,6,8}), 
			new Land(5, "GREY", new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0}, new int[]{4,5,7,8}),
			new Land(6, "GREY", new Double[]{0.0,150.0, 80.0,150.0, 90.0,170.0, 70.0,200.0, 0.0, 200.00}, new int[]{6,8}),
			new Land(7, "GREY", new Double[]{100.0,150.0, 150.0,150.0, 150.0,200.0, 70.0,200.0, 90.0,170.00, 80.0,150.0}, new int[]{5,6,7})));
	*/
	
	/*
	Double[] dbl1 = new Double[]{756.0,0.0,1012.0,-10.0,1048.0,64.0,1068.0,124.0,1076.0,178.0,1046.0,170.0,1000.0,142.0,954.0,172.0,952.0,192.0,888.0,160.0,860.0,182.0,818.0,184.0,778.0,212.0,732.0,258.0,670.0,278.0,600.0,264.0,604.0,250.0,632.0,216.0,624.0,194.0,646.0,160.0,622.0,122.0,684.0,82.0,706.0,82.0,710.0,56.0};
	Double[] dbl2 = new Double[]{1088.0,426.0,1086.0,380.0,1072.0,328.0,1042.0,292.0,1014.0,280.0,954.0,192.0,954.0,174.0,1002.0,146.0,1054.0,176.0,1074.0,176.0,1100.0,168.0,1114.0,182.0,1164.0,188.0,1214.0,216.0,1226.0,194.0,1278.0,188.0,1298.0,272.0,1286.0,310.0,1260.0,376.0,1242.0,352.0,1216.0,342.0,1208.0,314.0,1204.0,370.0,1186.0,426.0,1146.0,432.0,1148.0,456.0,1138.0,458.0,1110.0,434.0,1092.0,428.0};
	Double[] dbl3 = new Double[]{256.0,262.0,310.0,248.0,344.0,296.0,370.0,344.0,394.0,350.0,444.0,338.0,464.0,306.0,472.0,282.0,450.0,238.0,498.0,294.0,522.0,280.0,536.0,288.0,560.0,272.0,596.0,264.0,662.0,274.0,728.0,258.0,822.0,180.0,854.0,174.0,882.0,164.0,950.0,188.0,1010.0,280.0,1038.0,294.0,1070.0,330.0,1084.0,384.0,1088.0,426.0,1078.0,444.0,1088.0,456.0,1072.0,460.0,1094.0,482.0,1030.0,476.0,1016.0,452.0,986.0,442.0,966.0,416.0,942.0,420.0,860.0,510.0,832.0,518.0,784.0,602.0,776.0,644.0,792.0,672.0,754.0,704.0,744.0,738.0,760.0,770.0,774.0,794.0,748.0,806.0,714.0,800.0,680.0,814.0,666.0,808.0,612.0,824.0,564.0,822.0,548.0,802.0,508.0,800.0,456.0,824.0,468.0,780.0,490.0,776.0,490.0,744.0,532.0,682.0,552.0,608.0,552.0,574.0,528.0,560.0,494.0,522.0,466.0,528.0,440.0,502.0,434.0,444.0,394.0,420.0,320.0,440.0,310.0,412.0,280.0,404.0,310.0,380.0,324.0,380.0,308.0,322.0,316.0,294.0,288.0,272.0};
	Double[] dbl4 = new Double[]{156.0,656.0,162.0,550.0,204.0,486.0,268.0,520.0,282.0,512.0,292.0,450.0,322.0,444.0,394.0,416.0,434.0,448.0,442.0,502.0,464.0,526.0,482.0,526.0,488.0,524.0,524.0,554.0,552.0,580.0,530.0,684.0,488.0,740.0,488.0,774.0,464.0,780.0,442.0,796.0,424.0,830.0,396.0,824.0,382.0,810.0,392.0,786.0,388.0,768.0,378.0,768.0,374.0,790.0,366.0,814.0,336.0,822.0,334.0,838.0,280.0,838.0,284.0,820.0,260.0,808.0,258.0,792.0,232.0,780.0,238.0,750.0,208.0,708.0,224.0,664.0,222.0,634.0,208.0,636.0,208.0,662.0,154.0,692.0,168.0,656.0};
	Double[] dbl5 = new Double[]{1116.0,528.0,1082.0,514.0,1074.0,484.0,1034.0,480.0,1016.0,456.0,986.0,444.0,962.0,416.0,938.0,414.0,864.0,512.0,830.0,522.0,782.0,612.0,778.0,642.0,794.0,670.0,752.0,712.0,744.0,740.0,776.0,794.0,822.0,766.0,818.0,748.0,834.0,748.0,856.0,790.0,858.0,834.0,880.0,808.0,914.0,800.0,956.0,818.0,974.0,864.0,1016.0,852.0,1034.0,806.0,1048.0,786.0,1040.0,762.0,1006.0,770.0,986.0,742.0,948.0,718.0,936.0,668.0,982.0,638.0,970.0,636.0,970.0,620.0,980.0,594.0,1012.0,578.0,1026.0,558.0,1086.0,560.0,1118.0,550.0};
	Double[] dbl6 = new Double[]{1214.0,718.0,1184.0,702.0,1146.0,690.0,1152.0,626.0,1112.0,588.0,1084.0,560.0,1026.0,560.0,1016.0,574.0,980.0,596.0,968.0,634.0,978.0,646.0,936.0,666.0,950.0,720.0,986.0,744.0,1004.0,770.0,1036.0,760.0,1062.0,724.0,1056.0,682.0,1096.0,702.0,1138.0,702.0,1144.0,712.0,1176.0,724.0};
	Double[] dbl7 = new Double[]{162.0,1000.0,182.0,956.0,222.0,906.0,278.0,948.0,320.0,920.0,304.0,908.0,360.0,884.0,364.0,858.0,416.0,864.0,420.0,926.0,412.0,950.0,414.0,980.0,400.0,1008.0,314.0,1022.0,262.0,1034.0,186.0,1032.0,164.0,1014.0};
	Double[] dbl8 = new Double[]{452.0,1052.0,498.0,1036.0,532.0,1036.0,558.0,1048.0,566.0,1074.0,586.0,1026.0,578.0,954.0,606.0,888.0,616.0,838.0,554.0,842.0,474.0,860.0,442.0,874.0,416.0,862.0,422.0,922.0,414.0,948.0,416.0,970.0,400.0,1008.0,430.0,1030.0};
	Double[] dbl9 = new Double[]{618.0,840.0,674.0,822.0,678.0,818.0,720.0,800.0,748.0,804.0,770.0,800.0,778.0,794.0,818.0,772.0,830.0,818.0,808.0,842.0,810.0,866.0,798.0,884.0,778.0,884.0,770.0,914.0,770.0,938.0,752.0,944.0,744.0,994.0,714.0,1000.0,712.0,1028.0,742.0,1064.0,726.0,1092.0,702.0,1094.0,664.0,1102.0,632.0,1094.0,624.0,1094.0,608.0,1138.0,586.0,1122.0,586.0,1098.0,576.0,1090.0,570.0,1070.0,586.0,1024.0,578.0,946.0,608.0,892.0};
	 */

	Double[] dbl0 = new Double[]{756.0,0.0,1012.0,-10.0,1048.0,64.0,1068.0,124.0,1076.0,178.0,1046.0,170.0,1000.0,142.0,954.0,172.0,952.0,192.0,888.0,160.0,860.0,182.0,818.0,184.0,778.0,212.0,732.0,258.0,670.0,278.0,600.0,264.0,604.0,250.0,632.0,216.0,624.0,194.0,646.0,160.0,622.0,122.0,684.0,82.0,706.0,82.0,710.0,56.0};
	Double[] dbl1 = new Double[]{1088.0,426.0,1086.0,380.0,1072.0,328.0,1042.0,292.0,1014.0,280.0,954.0,192.0,954.0,174.0,1002.0,146.0,1054.0,176.0,1074.0,176.0,1100.0,168.0,1114.0,182.0,1164.0,188.0,1214.0,216.0,1226.0,194.0,1278.0,188.0,1298.0,272.0,1286.0,310.0,1260.0,376.0,1242.0,352.0,1216.0,342.0,1208.0,314.0,1204.0,370.0,1186.0,426.0,1146.0,432.0,1148.0,456.0,1138.0,458.0,1110.0,434.0,1092.0,428.0};
	Double[] dbl2 = new Double[]{256.0,262.0,310.0,248.0,344.0,296.0,370.0,344.0,394.0,350.0,444.0,338.0,464.0,306.0,472.0,282.0,450.0,238.0,498.0,294.0,522.0,280.0,536.0,288.0,560.0,272.0,596.0,264.0,662.0,274.0,728.0,258.0,822.0,180.0,854.0,174.0,882.0,164.0,950.0,188.0,1010.0,280.0,1038.0,294.0,1070.0,330.0,1084.0,384.0,1088.0,426.0,1078.0,444.0,1088.0,456.0,1072.0,460.0,1094.0,482.0,1030.0,476.0,1016.0,452.0,986.0,442.0,966.0,416.0,942.0,420.0,860.0,510.0,832.0,518.0,784.0,602.0,776.0,644.0,792.0,672.0,754.0,704.0,744.0,738.0,760.0,770.0,774.0,794.0,748.0,806.0,714.0,800.0,680.0,814.0,666.0,808.0,612.0,824.0,564.0,822.0,548.0,802.0,508.0,800.0,456.0,824.0,468.0,780.0,490.0,776.0,490.0,744.0,532.0,682.0,552.0,608.0,552.0,574.0,528.0,560.0,494.0,522.0,466.0,528.0,440.0,502.0,434.0,444.0,394.0,420.0,320.0,440.0,310.0,412.0,280.0,404.0,310.0,380.0,324.0,380.0,308.0,322.0,316.0,294.0,288.0,272.0};
	Double[] dbl3 = new Double[]{156.0,656.0,162.0,550.0,204.0,486.0,268.0,520.0,282.0,512.0,292.0,450.0,322.0,444.0,394.0,416.0,434.0,448.0,442.0,502.0,464.0,526.0,482.0,526.0,488.0,524.0,524.0,554.0,552.0,580.0,530.0,684.0,488.0,740.0,488.0,774.0,464.0,780.0,442.0,796.0,424.0,830.0,396.0,824.0,382.0,810.0,392.0,786.0,388.0,768.0,378.0,768.0,374.0,790.0,366.0,814.0,336.0,822.0,334.0,838.0,280.0,838.0,284.0,820.0,260.0,808.0,258.0,792.0,232.0,780.0,238.0,750.0,208.0,708.0,224.0,664.0,222.0,634.0,208.0,636.0,208.0,662.0,154.0,692.0,168.0,656.0};
	Double[] dbl4 = new Double[]{1116.0,528.0,1082.0,514.0,1074.0,484.0,1034.0,480.0,1016.0,456.0,986.0,444.0,962.0,416.0,938.0,414.0,864.0,512.0,830.0,522.0,782.0,612.0,778.0,642.0,794.0,670.0,752.0,712.0,744.0,740.0,776.0,794.0,822.0,766.0,818.0,748.0,834.0,748.0,856.0,790.0,858.0,834.0,880.0,808.0,914.0,800.0,956.0,818.0,974.0,864.0,1016.0,852.0,1034.0,806.0,1048.0,786.0,1040.0,762.0,1006.0,770.0,986.0,742.0,948.0,718.0,936.0,668.0,982.0,638.0,970.0,636.0,970.0,620.0,980.0,594.0,1012.0,578.0,1026.0,558.0,1086.0,560.0,1118.0,550.0};
	Double[] dbl5 = new Double[]{1214.0,718.0,1184.0,702.0,1146.0,690.0,1152.0,626.0,1112.0,588.0,1084.0,560.0,1026.0,560.0,1016.0,574.0,980.0,596.0,968.0,634.0,978.0,646.0,936.0,666.0,950.0,720.0,986.0,744.0,1004.0,770.0,1036.0,760.0,1062.0,724.0,1056.0,682.0,1096.0,702.0,1138.0,702.0,1144.0,712.0,1176.0,724.0};
	Double[] dbl6 = new Double[]{162.0,1000.0,182.0,956.0,222.0,906.0,278.0,948.0,320.0,920.0,304.0,908.0,360.0,884.0,364.0,858.0,416.0,864.0,420.0,926.0,412.0,950.0,414.0,980.0,400.0,1008.0,314.0,1022.0,262.0,1034.0,186.0,1032.0,164.0,1014.0};
	Double[] dbl7 = new Double[]{452.0,1052.0,498.0,1036.0,532.0,1036.0,558.0,1048.0,566.0,1074.0,586.0,1026.0,578.0,954.0,606.0,888.0,616.0,838.0,554.0,842.0,474.0,860.0,442.0,874.0,416.0,862.0,422.0,922.0,414.0,948.0,416.0,970.0,400.0,1008.0,430.0,1030.0};
	Double[] dbl8 = new Double[]{618.0,840.0,674.0,822.0,678.0,818.0,720.0,800.0,748.0,804.0,770.0,800.0,778.0,794.0,818.0,772.0,830.0,818.0,808.0,842.0,810.0,866.0,798.0,884.0,778.0,884.0,770.0,914.0,770.0,938.0,752.0,944.0,744.0,994.0,714.0,1000.0,712.0,1028.0,742.0,1064.0,726.0,1092.0,702.0,1094.0,664.0,1102.0,632.0,1094.0,624.0,1094.0,608.0,1138.0,586.0,1122.0,586.0,1098.0,576.0,1090.0,570.0,1070.0,586.0,1024.0,578.0,946.0,608.0,892.0};
	Double[] dbl9 = new Double[]{470.0,1204.0,488.0,1242.0,528.0,1224.0,564.0,1208.0,570.0,1220.0,558.0,1220.0,562.0,1270.0,612.0,1270.0,644.0,1268.0,654.0,1272.0,698.0,1294.0,722.0,1300.0,728.0,1292.0,686.0,1238.0,642.0,1196.0,592.0,1128.0,588.0,1128.0,586.0,1094.0,578.0,1092.0,570.0,1066.0,560.0,1042.0,532.0,1028.0,486.0,1042.0,460.0,1058.0,488.0,1074.0,516.0,1110.0,532.0,1136.0,544.0,1144.0,548.0,1166.0,528.0,1186.0,508.0,1194.0} ;
	Double[] dbl10 = new Double[]{734.0,1274.0,714.0,1258.0,700.0,1230.0,638.0,1168.0,642.0,1154.0,624.0,1150.0,622.0,1132.0,638.0,1096.0,666.0,1102.0,726.0,1090.0,740.0,1068.0,760.0,1064.0,780.0,1080.0,804.0,1066.0,838.0,1062.0,866.0,1096.0,850.0,1128.0,864.0,1166.0,884.0,1190.0,882.0,1198.0,850.0,1196.0,810.0,1230.0,768.0,1234.0,734.0,1280.0,732.0,1278.0};
	Double[] dbl11 = new Double[]{842.0,1060.0,866.0,1096.0,852.0,1128.0,868.0,1170.0,884.0,1190.0,928.0,1190.0,942.0,1186.0,984.0,1198.0,1010.0,1186.0,1042.0,1186.0,1088.0,1152.0,1128.0,1084.0,1188.0,1068.0,1236.0,1046.0,1294.0,996.0,1218.0,1036.0,1184.0,1058.0,1172.0,1052.0,1240.0,1006.0,1228.0,998.0,1152.0,1060.0,1124.0,1050.0,1136.0,1030.0,1168.0,1016.0,1200.0,974.0,1110.0,1028.0,1106.0,1016.0,1156.0,986.0,1214.0,946.0,1142.0,966.0,1156.0,978.0,1100.0,996.0,1046.0,1008.0,1046.0,994.0,1060.0,978.0,1054.0,970.0,1036.0,988.0,1014.0,988.0,994.0,1010.0,948.0,1028.0,934.0,1016.0,914.0,1024.0,916.0,1044.0,908.0,1046.0,880.0,1036.0,872.0,1042.0,876.0,1058.0};
	Double[] dbl12 = new Double[]{840.0,1414.0,820.0,1394.0,816.0,1370.0,800.0,1348.0,768.0,1314.0,734.0,1278.0,768.0,1234.0,810.0,1230.0,854.0,1194.0,880.0,1196.0,888.0,1190.0,934.0,1190.0,940.0,1186.0,986.0,1198.0,1004.0,1188.0,1040.0,1188.0,1090.0,1150.0,1102.0,1142.0,1138.0,1132.0,1166.0,1112.0,1212.0,1106.0,1254.0,1094.0,1306.0,1064.0,1290.0,1096.0,1264.0,1116.0,1240.0,1126.0,1236.0,1138.0,1254.0,1138.0,1260.0,1158.0,1274.0,1156.0,1284.0,1138.0,1296.0,1130.0,1300.0,1144.0,1294.0,1152.0,1308.0,1160.0,1306.0,1178.0,1288.0,1180.0,1268.0,1178.0,1266.0,1202.0,1278.0,1208.0,1230.0,1226.0,1206.0,1220.0,1206.0,1244.0,1196.0,1260.0,1166.0,1266.0,1114.0,1292.0,1090.0,1288.0,1082.0,1270.0,1024.0,1276.0,1002.0,1294.0,1022.0,1310.0,956.0,1316.0,946.0,1334.0,974.0,1348.0,968.0,1356.0,1010.0,1376.0,1028.0,1398.0,1052.0,1406.0,1074.0,1426.0,1096.0,1436.0,1100.0,1448.0,1074.0,1462.0,1038.0,1458.0,1030.0,1468.0,990.0,1464.0,950.0,1454.0,870.0,1410.0};
	Double[] dbl13 = new Double[]{1326.0,1298.0,1346.0,1272.0,1304.0,1268.0,1260.0,1270.0,1236.0,1284.0,1216.0,1282.0,1184.0,1292.0,1164.0,1290.0,1114.0,1298.0,1096.0,1292.0,1080.0,1272.0,1024.0,1274.0,1004.0,1294.0,1026.0,1312.0,954.0,1316.0,948.0,1334.0,976.0,1346.0,972.0,1352.0,1004.0,1372.0,1030.0,1398.0,1054.0,1400.0,1068.0,1422.0,1098.0,1436.0,1100.0,1450.0,1132.0,1442.0,1166.0,1412.0,1178.0,1394.0,1204.0,1394.0,1176.0,1382.0,1190.0,1350.0,1162.0,1336.0,1176.0,1322.0,1186.0,1312.0,1218.0,1310.0,1232.0,1330.0,1252.0,1310.0,1260.0,1324.0,1288.0,1322.0,1310.0,1298.0};
	Double[] dbl14 = new Double[]{488.0,1372.0,538.0,1356.0,554.0,1330.0,570.0,1328.0,578.0,1312.0,604.0,1312.0,624.0,1270.0,654.0,1272.0,682.0,1286.0,710.0,1298.0,728.0,1296.0,766.0,1332.0,784.0,1356.0,806.0,1376.0,810.0,1404.0,822.0,1418.0,792.0,1422.0,780.0,1408.0,756.0,1414.0,718.0,1410.0,696.0,1396.0,680.0,1408.0,670.0,1424.0,634.0,1436.0,616.0,1468.0,600.0,1492.0,550.0,1508.0,524.0,1500.0,522.0,1482.0,498.0,1466.0,506.0,1442.0,516.0,1422.0,502.0,1398.0,488.0,1384.0};
	Double[] dbl15 = new Double[]{368.0,1418.0,342.0,1392.0,356.0,1380.0,382.0,1374.0,408.0,1390.0,430.0,1376.0,452.0,1380.0,466.0,1368.0,484.0,1370.0,486.0,1384.0,502.0,1400.0,514.0,1420.0,510.0,1428.0,500.0,1466.0,516.0,1480.0,522.0,1484.0,520.0,1496.0,522.0,1516.0,514.0,1524.0,500.0,1522.0,474.0,1548.0,456.0,1550.0,466.0,1574.0,458.0,1574.0,466.0,1594.0,462.0,1612.0,468.0,1628.0,456.0,1628.0,454.0,1636.0,436.0,1656.0,414.0,1662.0,400.0,1674.0,378.0,1668.0,352.0,1682.0,312.0,1678.0,280.0,1670.0,282.0,1642.0,264.0,1654.0,234.0,1654.0,208.0,1676.0,186.0,1682.0,214.0,1654.0,202.0,1642.0,236.0,1630.0,250.0,1608.0,274.0,1602.0,282.0,1586.0,316.0,1580.0,338.0,1552.0,348.0,1512.0,334.0,1496.0,340.0,1478.0,352.0,1476.0,358.0,1450.0,368.0,1434.0};
	Double[] dbl16 = new Double[]{462.0,1736.0,462.0,1676.0,468.0,1670.0,456.0,1628.0,468.0,1628.0,464.0,1620.0,466.0,1594.0,456.0,1582.0,456.0,1576.0,468.0,1576.0,454.0,1550.0,478.0,1548.0,502.0,1526.0,516.0,1528.0,520.0,1522.0,524.0,1494.0,542.0,1512.0,602.0,1494.0,636.0,1434.0,670.0,1426.0,686.0,1444.0,668.0,1464.0,684.0,1488.0,722.0,1536.0,712.0,1572.0,710.0,1586.0,726.0,1604.0,714.0,1626.0,682.0,1626.0,654.0,1656.0,638.0,1686.0,608.0,1696.0,586.0,1682.0,560.0,1696.0,546.0,1694.0,536.0,1698.0,516.0,1720.0,502.0,1734.0,474.0,1724.0};
	Double[] dbl17 = new Double[]{822.0,1610.0,832.0,1598.0,854.0,1598.0,866.0,1588.0,878.0,1526.0,876.0,1512.0,868.0,1498.0,860.0,1436.0,858.0,1426.0,834.0,1426.0,824.0,1416.0,790.0,1420.0,784.0,1412.0,746.0,1412.0,724.0,1414.0,696.0,1396.0,668.0,1428.0,682.0,1442.0,668.0,1468.0,722.0,1540.0,708.0,1586.0,722.0,1604.0,754.0,1594.0,790.0,1602.0};
	Double[] dbl18 = new Double[]{226.0,1924.0,248.0,1906.0,226.0,1874.0,232.0,1824.0,250.0,1822.0,260.0,1776.0,266.0,1740.0,276.0,1734.0,276.0,1728.0,288.0,1694.0,282.0,1666.0,324.0,1684.0,350.0,1682.0,356.0,1682.0,380.0,1670.0,404.0,1676.0,420.0,1656.0,434.0,1660.0,462.0,1626.0,470.0,1670.0,460.0,1676.0,456.0,1736.0,468.0,1770.0,464.0,1796.0,456.0,1816.0,464.0,1866.0,480.0,1892.0,468.0,1904.0,456.0,1952.0,460.0,1962.0,384.0,1920.0,372.0,1918.0,364.0,1902.0,336.0,1900.0,312.0,1918.0,264.0,1926.0} ;
	Double[] dbl19 = new Double[]{862.0,1800.0,858.0,1764.0,864.0,1722.0,864.0,1686.0,840.0,1654.0,842.0,1622.0,826.0,1610.0,754.0,1596.0,726.0,1606.0,718.0,1624.0,678.0,1626.0,658.0,1652.0,640.0,1688.0,606.0,1694.0,586.0,1684.0,560.0,1694.0,530.0,1694.0,542.0,1702.0,504.0,1732.0,456.0,1722.0,468.0,1772.0,458.0,1812.0,466.0,1880.0,480.0,1894.0,522.0,1896.0,558.0,1886.0,578.0,1908.0,644.0,1896.0,662.0,1888.0,692.0,1890.0,712.0,1868.0,742.0,1870.0,756.0,1838.0,788.0,1826.0,830.0,1792.0,848.0,1794.0};
	Double[] dbl20 = new Double[]{1246.0,1486.0,1220.0,1516.0,1186.0,1514.0,1186.0,1534.0,1148.0,1532.0,1148.0,1550.0,1134.0,1554.0,1126.0,1542.0,1114.0,1542.0,1114.0,1554.0,1078.0,1576.0,1086.0,1592.0,1056.0,1610.0,1036.0,1652.0,1002.0,1652.0,976.0,1654.0,960.0,1654.0,936.0,1644.0,880.0,1668.0,866.0,1686.0,838.0,1650.0,844.0,1622.0,830.0,1608.0,834.0,1596.0,858.0,1594.0,870.0,1590.0,880.0,1512.0,872.0,1506.0,860.0,1428.0,876.0,1430.0,942.0,1464.0,950.0,1468.0,966.0,1488.0,1014.0,1508.0,1032.0,1506.0,1058.0,1492.0,1086.0,1492.0,1130.0,1508.0,1146.0,1502.0,1164.0,1488.0,1190.0,1470.0,1210.0,1466.0,1266.0,1414.0,1264.0,1450.0,1232.0,1468.0} ;
	Double[] dbl21 = new Double[]{914.0,1894.0,962.0,1896.0,1004.0,1872.0,1068.0,1822.0,1108.0,1796.0,1102.0,1752.0,1056.0,1768.0,994.0,1784.0,958.0,1780.0,958.0,1762.0,968.0,1756.0,958.0,1740.0,992.0,1736.0,992.0,1726.0,1008.0,1704.0,1026.0,1704.0,1026.0,1726.0,1038.0,1708.0,1048.0,1676.0,1062.0,1668.0,1060.0,1648.0,1030.0,1654.0,966.0,1656.0,936.0,1648.0,886.0,1662.0,866.0,1688.0,862.0,1742.0,860.0,1778.0,866.0,1800.0,862.0,1850.0,874.0,1866.0,894.0,1876.0};

	
	private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(0, "GREY", dbl0, new int[]{1}), 
			new Land(1, "GREY", dbl1, new int[]{1}),
			new Land(2, "GREY", dbl2, new int[]{1}),
			new Land(3, "GREY", dbl3, new int[]{1}),
			new Land(4, "GREY", dbl4, new int[]{1}), 
			new Land(5, "GREY", dbl5, new int[]{1}),
			new Land(6, "GREY", dbl6, new int[]{1}),
			new Land(7, "GREY", dbl7, new int[]{1}),
			new Land(8, "GREY", dbl8, new int[]{1}),
			new Land(9, "GREY", dbl9, new int[]{1}),
			new Land(10, "GREY", dbl10, new int[]{1}),
			new Land(11, "GREY", dbl11, new int[]{1}),
			new Land(12, "GREY", dbl12, new int[]{1}),
			new Land(13, "GREY", dbl13, new int[]{1}),
			new Land(14, "GREY", dbl14, new int[]{1}),
			new Land(15, "GREY", dbl15, new int[]{1}),
			new Land(16, "GREY", dbl16, new int[]{1}),
			new Land(17, "GREY", dbl17, new int[]{1}),
			new Land(18, "GREY", dbl18, new int[]{1}),
			new Land(19, "GREY", dbl19, new int[]{1}),
			new Land(20, "GREY", dbl20, new int[]{1}),
			new Land(21, "GREY", dbl21, new int[]{1})));
	
	public List<Land> getGameState()
	{
		return gameState;
	}

	public void setGameState(List<Land> gameState)
	{
		this.gameState = gameState;
	}

	public void updateGameState(int pid, int area)
	{
		for(Land land : gameState){
			if(land.getLandId() ==  area)
				switch (pid){
				case 0:
					land.setColor("RED");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 1:
					land.setColor("BLUE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 2:
					land.setColor("YELLOW");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 3:
					land.setColor("GREEN");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 4:
					land.setColor("ORANGE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 5:
					land.setColor("PURPLE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				}

		}	
	}
}








