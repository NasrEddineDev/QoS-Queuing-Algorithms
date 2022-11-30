package com.mesbahhightech.qosqueuingalgorithms.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExampleDao_Impl implements ExampleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Example> __insertionAdapterOfExample;

  private final EntityDeletionOrUpdateAdapter<Example> __deletionAdapterOfExample;

  private final EntityDeletionOrUpdateAdapter<Example> __updateAdapterOfExample;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllExamples;

  public ExampleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExample = new EntityInsertionAdapter<Example>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `example` (`id`,`name`,`rows`,`columns`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Example value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getRows());
        stmt.bindLong(4, value.getColumns());
      }
    };
    this.__deletionAdapterOfExample = new EntityDeletionOrUpdateAdapter<Example>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `example` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Example value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfExample = new EntityDeletionOrUpdateAdapter<Example>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `example` SET `id` = ?,`name` = ?,`rows` = ?,`columns` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Example value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getRows());
        stmt.bindLong(4, value.getColumns());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllExamples = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM example";
        return _query;
      }
    };
  }

  @Override
  public long insert(final Example example) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfExample.insertAndReturnId(example);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Example example) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExample.handle(example);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Example example) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfExample.handle(example);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllExamples() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllExamples.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllExamples.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Example>> getAllExamples() {
    final String _sql = "SELECT * FROM example ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"example"}, false, new Callable<List<Example>>() {
      @Override
      public List<Example> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRows = CursorUtil.getColumnIndexOrThrow(_cursor, "rows");
          final int _cursorIndexOfColumns = CursorUtil.getColumnIndexOrThrow(_cursor, "columns");
          final List<Example> _result = new ArrayList<Example>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Example _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpRows;
            _tmpRows = _cursor.getInt(_cursorIndexOfRows);
            final int _tmpColumns;
            _tmpColumns = _cursor.getInt(_cursorIndexOfColumns);
            _item = new Example(_tmpName,_tmpRows,_tmpColumns);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Example> getAllExamples1() {
    final String _sql = "SELECT * FROM example ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfRows = CursorUtil.getColumnIndexOrThrow(_cursor, "rows");
      final int _cursorIndexOfColumns = CursorUtil.getColumnIndexOrThrow(_cursor, "columns");
      final List<Example> _result = new ArrayList<Example>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Example _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpRows;
        _tmpRows = _cursor.getInt(_cursorIndexOfRows);
        final int _tmpColumns;
        _tmpColumns = _cursor.getInt(_cursorIndexOfColumns);
        _item = new Example(_tmpName,_tmpRows,_tmpColumns);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Example getExampleById(final int example_id) {
    final String _sql = "SELECT * FROM example where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, example_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfRows = CursorUtil.getColumnIndexOrThrow(_cursor, "rows");
      final int _cursorIndexOfColumns = CursorUtil.getColumnIndexOrThrow(_cursor, "columns");
      final Example _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpRows;
        _tmpRows = _cursor.getInt(_cursorIndexOfRows);
        final int _tmpColumns;
        _tmpColumns = _cursor.getInt(_cursorIndexOfColumns);
        _result = new Example(_tmpName,_tmpRows,_tmpColumns);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Example getExampleByName(final String name) {
    final String _sql = "SELECT * FROM example where name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfRows = CursorUtil.getColumnIndexOrThrow(_cursor, "rows");
      final int _cursorIndexOfColumns = CursorUtil.getColumnIndexOrThrow(_cursor, "columns");
      final Example _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpRows;
        _tmpRows = _cursor.getInt(_cursorIndexOfRows);
        final int _tmpColumns;
        _tmpColumns = _cursor.getInt(_cursorIndexOfColumns);
        _result = new Example(_tmpName,_tmpRows,_tmpColumns);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
