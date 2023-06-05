package comissao.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Moeda {

	public static Locale BRAZIL = new Locale("pt","BR");
	private static DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
        public static DecimalFormat FORMATO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);
}
