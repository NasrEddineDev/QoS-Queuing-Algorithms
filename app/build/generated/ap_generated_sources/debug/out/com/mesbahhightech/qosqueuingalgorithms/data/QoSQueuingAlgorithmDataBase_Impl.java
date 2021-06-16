package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class QoSQueuingAlgorithmDataBase_Impl extends QoSQueuingAlgorithmDataBase {
  private volatile ExampleDao _exampleDao;

  private volatile AlgorithmDao _algorithmDao;

  private volatile QueueDao _queueDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `example` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `rows` INTEGER NOT NULL, `columns` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `algorithm` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `description` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `queue` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `content` TEXT, `example_id` INTEGER NOT NULL, FOREIGN KEY(`example_id`) REFERENCES `example`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '32b3f623e4a0b7ceeb45d39be4d79c94')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `example`");
        _db.execSQL("DROP TABLE IF EXISTS `algorithm`");
        _db.execSQL("DROP TABLE IF EXISTS `queue`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsExample = new HashMap<String, TableInfo.Column>(4);
        _columnsExample.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExample.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExample.put("rows", new TableInfo.Column("rows", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExample.put("columns", new TableInfo.Column("columns", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExample = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExample = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExample = new TableInfo("example", _columnsExample, _foreignKeysExample, _indicesExample);
        final TableInfo _existingExample = TableInfo.read(_db, "example");
        if (! _infoExample.equals(_existingExample)) {
          return new RoomOpenHelper.ValidationResult(false, "example(com.mesbahhightech.qosqueuingalgorithms.data.Example).\n"
                  + " Expected:\n" + _infoExample + "\n"
                  + " Found:\n" + _existingExample);
        }
        final HashMap<String, TableInfo.Column> _columnsAlgorithm = new HashMap<String, TableInfo.Column>(3);
        _columnsAlgorithm.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlgorithm.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlgorithm.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlgorithm = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlgorithm = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlgorithm = new TableInfo("algorithm", _columnsAlgorithm, _foreignKeysAlgorithm, _indicesAlgorithm);
        final TableInfo _existingAlgorithm = TableInfo.read(_db, "algorithm");
        if (! _infoAlgorithm.equals(_existingAlgorithm)) {
          return new RoomOpenHelper.ValidationResult(false, "algorithm(com.mesbahhightech.qosqueuingalgorithms.data.Algorithm).\n"
                  + " Expected:\n" + _infoAlgorithm + "\n"
                  + " Found:\n" + _existingAlgorithm);
        }
        final HashMap<String, TableInfo.Column> _columnsQueue = new HashMap<String, TableInfo.Column>(4);
        _columnsQueue.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueue.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueue.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQueue.put("example_id", new TableInfo.Column("example_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQueue = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysQueue.add(new TableInfo.ForeignKey("example", "CASCADE", "NO ACTION",Arrays.asList("example_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesQueue = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQueue = new TableInfo("queue", _columnsQueue, _foreignKeysQueue, _indicesQueue);
        final TableInfo _existingQueue = TableInfo.read(_db, "queue");
        if (! _infoQueue.equals(_existingQueue)) {
          return new RoomOpenHelper.ValidationResult(false, "queue(com.mesbahhightech.qosqueuingalgorithms.data.Queue).\n"
                  + " Expected:\n" + _infoQueue + "\n"
                  + " Found:\n" + _existingQueue);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "32b3f623e4a0b7ceeb45d39be4d79c94", "ee20ca0aebf666f9c9d68d599c0d69ba");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "example","algorithm","queue");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `example`");
      _db.execSQL("DELETE FROM `algorithm`");
      _db.execSQL("DELETE FROM `queue`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ExampleDao exampleDao() {
    if (_exampleDao != null) {
      return _exampleDao;
    } else {
      synchronized(this) {
        if(_exampleDao == null) {
          _exampleDao = new ExampleDao_Impl(this);
        }
        return _exampleDao;
      }
    }
  }

  @Override
  public AlgorithmDao algorithmDao() {
    if (_algorithmDao != null) {
      return _algorithmDao;
    } else {
      synchronized(this) {
        if(_algorithmDao == null) {
          _algorithmDao = new AlgorithmDao_Impl(this);
        }
        return _algorithmDao;
      }
    }
  }

  @Override
  public QueueDao queueDao() {
    if (_queueDao != null) {
      return _queueDao;
    } else {
      synchronized(this) {
        if(_queueDao == null) {
          _queueDao = new QueueDao_Impl(this);
        }
        return _queueDao;
      }
    }
  }
}
