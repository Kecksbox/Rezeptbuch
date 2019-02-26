package simple_mvc.repository.converters;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.Mapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wird verwendet, um {@link ObservableList ObservableLists} im Zusammenspiel
 * mit {@link XStream} zu unterst&uuml;tzen.
 * 
 * @author Manuel Seiche
 * @since 25.02.2019
 */
public final class ObservableListConverter extends CollectionConverter implements Converter {

	/**
	 * Erzeugt eine Instanz von {@link ObservableListConverter}
	 * 
	 * @param xstream {@link XStream}
	 */
	public ObservableListConverter(final XStream xstream) {
		super(xstream.getMapper());
	}

	/**
	 * Erzeugt eine Instanz von {@link ObservableListConverter}
	 * 
	 * @param mapper zum Beispiel {@link XStream#getMapper()}
	 */
	public ObservableListConverter(final Mapper mapper) {
		super(mapper);
	}

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") final Class type) {
		return ObservableList.class.isAssignableFrom(type);
	}

	@Override
	protected Object createCollection(@SuppressWarnings("rawtypes") final Class type) {
		return FXCollections.observableArrayList();
	}
}