package br.com.usinasantafe.pst.pst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.usinasantafe.pst.bean.estaticas.AreaBean;
import br.com.usinasantafe.pst.bean.estaticas.SubAreaBean;
import br.com.usinasantafe.pst.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pst.bean.estaticas.TipoBean;
import br.com.usinasantafe.pst.bean.estaticas.TopicoBean;
import br.com.usinasantafe.pst.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pst.bean.variaveis.CabAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.ItemAbordBean;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "pst_db";
	public static final int FORCA_BD_VERSION = 2;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {

		super(context, FORCA_DB_NAME, null, FORCA_BD_VERSION);
		instance = this;
		
	}

	@Override
	public void close() {
		super.close();
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
		
		try{

			TableUtils.createTable(cs, AreaBean.class);
			TableUtils.createTable(cs, SubAreaBean.class);
			TableUtils.createTable(cs, ColabBean.class);
			TableUtils.createTable(cs, QuestaoBean.class);
			TableUtils.createTable(cs, TipoBean.class);
			TableUtils.createTable(cs, TopicoBean.class);
			TableUtils.createTable(cs, TurnoBean.class);

			TableUtils.createTable(cs, CabAbordBean.class);
			TableUtils.createTable(cs, ItemAbordBean.class);
			TableUtils.createTable(cs, FotoAbordBean.class);

		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){

				TableUtils.dropTable(cs, AreaBean.class, true);
				TableUtils.dropTable(cs, SubAreaBean.class, true);
				TableUtils.dropTable(cs, ColabBean.class, true);
				TableUtils.dropTable(cs, QuestaoBean.class, true);
				TableUtils.dropTable(cs, TipoBean.class, true);
				TableUtils.dropTable(cs, TopicoBean.class, true);
				TableUtils.dropTable(cs, TurnoBean.class, true);

				TableUtils.dropTable(cs, CabAbordBean.class, true);
				TableUtils.dropTable(cs, ItemAbordBean.class, true);
				TableUtils.dropTable(cs, FotoAbordBean.class, true);

				TableUtils.createTable(cs, AreaBean.class);
				TableUtils.createTable(cs, SubAreaBean.class);
				TableUtils.createTable(cs, ColabBean.class);
				TableUtils.createTable(cs, QuestaoBean.class);
				TableUtils.createTable(cs, TipoBean.class);
				TableUtils.createTable(cs, TopicoBean.class);
				TableUtils.createTable(cs, TurnoBean.class);

				TableUtils.createTable(cs, CabAbordBean.class);
				TableUtils.createTable(cs, ItemAbordBean.class);
				TableUtils.createTable(cs, FotoAbordBean.class);

//				TableUtils.createTable(cs, ConfigBean.class);
//				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
