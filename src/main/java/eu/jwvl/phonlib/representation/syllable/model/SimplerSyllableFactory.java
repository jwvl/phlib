package eu.jwvl.phonlib.representation.syllable.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import eu.jwvl.phonlib.representation.segment.CharPhoneEncoder;
import eu.jwvl.phonlib.symbol.encoding.trie.NodeType;

import java.util.List;

/**
 * Created by janwillem on 05/03/2017.
 */
public class SimplerSyllableFactory {
    private Table<NodeType,String,SimplerSyllablePart> table;
    private int counter;
    private CharPhoneEncoder phoneDecoder;
    private List<SimplerSyllablePart> intList;

    public SimplerSyllableFactory(CharPhoneEncoder phoneDecoder) {
        this.table = HashBasedTable.create();
        this.phoneDecoder = phoneDecoder;
    }

    public SimplerSyllablePart get(NodeType nodeType, String contents) {
        SimplerSyllablePart result = table.get(nodeType, contents);
        if (result == null) {
            result = createNewSimplerSyllable(nodeType, contents);
            table.put(nodeType,contents,result);
        }
        return result;
    }

    private SimplerSyllablePart createNewSimplerSyllable(NodeType nodeType, String string) {
        byte[] asBytes = phoneDecoder.getBytes(string);
        SimplerSyllablePart result = new SimplerSyllablePart(nodeType, string, asBytes, counter++);
        intList.add(result);
        return result;
    }

    public SimplerSyllablePart getByIndex(int index) {
        return intList.get(index);
    }
}
