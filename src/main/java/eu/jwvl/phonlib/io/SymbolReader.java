/**
 * 
 */
package eu.jwvl.phonlib.io;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.jwvl.phonlib.symbol.PhoneticUnit;
import eu.jwvl.phonlib.symbol.PhoneticUnitCache;
import eu.jwvl.phonlib.symbol.PhoneticUnitFactory;
import eu.jwvl.phonlib.symbol.encoding.PhoneticEncoding;
import eu.jwvl.phonlib.symbol.encoding.PhoneticEncodingMap;
import eu.jwvl.phonlib.util.string.MyStringTable;

/**
 * @author jwvl
 * @date Aug 27, 2015
 *
 */
public class SymbolReader {


	public static PhoneticUnitCache initSymbolsFromFile(String filename, String separator) {
		PhoneticUnitFactory factory = new PhoneticUnitFactory();
		MyStringTable stringTable = MyStringTable.fromFile(filename, true, separator);
		List<String> enumColumns = getPhoneticEnumColumns(stringTable.getColumnNames());
		for (int i = 0; i < stringTable.getNumRows(); i++) {
			Map<PhoneticEncoding, String> asMap = createMap(stringTable, i, enumColumns);
			PhoneticEncodingMap phoneticEncodingMap = PhoneticEncodingMap.createFromMap(asMap);
			String classification = stringTable.getString(i, "Classification");
			PhoneticUnit result = factory.createFromMapAndClassification(phoneticEncodingMap, classification);
			if (result != null) {
				System.out.printf("Created phonetic unit %s%n",result.toVerboseString());
			}
		}
		return factory.getCache();
	}

	/**
	 * @param stringTable
	 * @param i
	 * @param enumColumns
	 * @return
	 */
	private static Map<PhoneticEncoding, String> createMap(MyStringTable stringTable, int i,
			List<String> enumColumns) {
		Map<PhoneticEncoding, String> result = Maps.newHashMap();
		for (String columnName : enumColumns) {
			String currentString = stringTable.getString(i, columnName);
			PhoneticEncoding encoding = PhoneticEncoding.valueOf(columnName.toUpperCase());
			result.put(encoding, currentString);
		}
		return result;
	}

	private static List<String> getPhoneticEnumColumns(List<String> columnNames) {
		List<String> result = Lists.newArrayList();
		for (String column : columnNames) {
			try {
				PhoneticEncoding found = PhoneticEncoding.valueOf(column.toUpperCase());
				result.add(column);
			} catch (IllegalArgumentException e) {
				System.err.println("No enum type for column " + column + ":: " + e.getMessage());
			}
		}
		return result;
	}

}
