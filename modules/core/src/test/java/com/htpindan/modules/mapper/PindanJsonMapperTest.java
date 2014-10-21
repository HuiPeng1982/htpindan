package com.htpindan.modules.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class PindanJsonMapperTest {

	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
	@Test
	public void fromJson() throws Exception {
		// List<String>
		String listString = "[{\"order_id\":\"110-8285344-2940223\",\"supplier\":\"AmazonUS\",\"billing_info\":\"Item Subtotal:$133.90 Shipping & Handling: $8.84 Promotion Applied: -$8.84 Total Before Tax: $133.90 Order Total: $133.90\",\"state\":1,\"order_date\":\"Friday, June 13, 2014\",\"order_items\":[{\"url\":\"http://www.amazon.com/dp/B00HU7DM8I/\",\"original_id\":\"B00HU7DM8I\",\"img\":\"http://ecx.images-amazon.com/images/I/41YixYqtpDL.jpg\",\"name\":\"Beaba First Stage Soft Spoons - Set of Four in Latte Color\",\"tag\":\"Baby Product\",\"soldBy\":\"Jolity\",\"soldByUrl\":\"http://www.amazon.com/gp/r.html?R=3I6BV9VWO5JJZ&C=3T5MUR8F9569O&H=V84H4G190R6IUHXDADXAWZQ4CZGA&T=C&U=http%3A%2F%2Fwww.amazon.com%2Fgp%2Fhelp%2Fseller%2Fhome.html%2Fref%3Dpe_385040_30332200_TE_seller%3Fie%3DUTF8%26seller%3DAIEUZANEXEI7Q\",\"condition\":\"New\",\"price\":\"$19.95\"},{\"url\":\"http://www.amazon.com/dp/B00FNJ7GVO/\",\"original_id\":\"B00FNJ7GVO\",\"img\":\"http://ecx.images-amazon.com/images/I/31vXpC6TmgL.jpg\",\"name\":\"BABYBJORN Plate and Spoon, Turquoise/Orange, 2-Count\",\"tag\":\"Baby Product\",\"soldBy\":\"Amazon.com LLC\",\"price\":\"$29.95\"},{\"url\":\"http://www.amazon.com/dp/B00GIKJ5E8/\",\"original_id\":\"B00GIKJ5E8\",\"img\":\"http://ecx.images-amazon.com/images/I/31iArZ163cL.jpg\",\"name\":\"Mini Melissa Melissa Ultragirl Sweet II Flat (Little Kid),Purple,2 M US Little Kid\",\"tag\":\"Apparel\",\"soldBy\":\"Amazon.com LLC\",\"price\":\"$65.00\"},{\"url\":\"http://www.amazon.com/dp/B005U54JLG/\",\"original_id\":\"B005U54JLG\",\"img\":\"http://ecx.images-amazon.com/images/I/41jOCW3LyGL.jpg\",\"name\":\"Zoli Baby Stuck Suction Feeding Bowl Kit - Pink\",\"tag\":\"Baby Product\",\"soldBy\":\"Zerbert\",\"soldByUrl\":\"http://www.amazon.com/gp/r.html?R=3I6BV9VWO5JJZ&C=3T5MUR8F9569O&H=JVKQZNAOJ6ALBBUTXAA1AR5Q4EAA&T=C&U=http%3A%2F%2Fwww.amazon.com%2Fgp%2Fhelp%2Fseller%2Fhome.html%2Fref%3Dpe_385040_30332200_TE_seller%3Fie%3DUTF8%26seller%3DABUX1LH52S4Z6\",\"condition\":\"New\",\"price\":\"$19.00\"}],\"subtotal\":\"$133.90\",\"applied\":\"-$8.84\",\"shipping_or_handling\":\"$8.84\",\"order_total\":\"$133.90\",\"delivery_to\":\"XRPMW XCEX 2110 NW Aloclek Drive ste 614 104162 hillsboro, OR 97124 United States\"}]";
		//String listString = "[{\"order_id\":\"110-8285344-2940223\",\"supplier\":\"AmazonUS\",\"state\":2,\"delivery_tracking\":\"http://www.amazon.com/gp/r.html?R=3I6BV9VWO5JJZ&C=3TG14KBDH777U&H=NZ2AMCCD59PATATYWHAVUTGDHOCA&T=C&U=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcss%2Fshiptrack%2Fview.html%2Fref%3Dpe_385040_30332190_TE_typ%3Fie%3DUTF8%26addressID%3Djoojkxioljp%26latestArrivalDate%3D1403233200%26orderID%3D110-8285344-2940223%26shipmentDate%3D1402943289%26orderingShipmentId%3D2764619037123%26packageId%3D1\",\"delivery_date\":\"Thursday, June 19, 2014\"}]";
		List<Map<String, Object>> mapList = binder.getMapper().readValue(listString, List.class);
		System.out.println("Order List:");
		for (Map<String, Object> element : mapList) {
			Object items = null;
			Integer state = Integer.getInteger("0");
			System.out.println(element);
			for(String key : element.keySet() ){
				if("state".equals(key)){
					state = (Integer) element.get(key);
				}
				if(!"order_items".equals(key)){
					System.out.println(key + " : " + element.get(key));
				}else{
					items = element.get(key);
				}
			}
			if(state.intValue() == 1){
				System.out.println("Item List:");
				System.out.println(items);
				for(Map<String, Object> item :  ((List<Map<String, Object>>) items)){
					for(String ikey : item.keySet() ){
						System.out.println("|____" + ikey + " : " + item.get(ikey));
					}
					System.out.println("|____ this one end  ____|");
				}
			}
		}
		
	}
}
