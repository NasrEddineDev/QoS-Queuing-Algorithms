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
public final class AlgorithmDao_Impl implements AlgorithmDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Algorithm> __insertionAdapterOfAlgorithm;

  private final EntityDeletionOrUpdateAdapter<Algorithm> __deletionAdapterOfAlgorithm;

  private final EntityDeletionOrUpdateAdapter<Algorithm> __updateAdapterOfAlgorithm;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAlgorithms;

  public AlgorithmDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlgorithm = new EntityInsertionAdapter<Algorithm>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `algorithm` (`id`,`name`,`description`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Algorithm value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
      }
    };
    this.__deletionAdapterOfAlgorithm = new EntityDeletionOrUpdateAdapter<Algorithm>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `algorithm` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Algorithm value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAlgorithm = new EntityDeletionOrUpdateAdapter<Algorithm>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `algorithm` SET `id` = ?,`name` = ?,`description` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Algorithm value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllAlgorithms = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM algorithm";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Algorithm algorithm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlgorithm.insert(algorithm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Algorithm algorithm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAlgorithm.handle(algorithm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Algorithm algorithm) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAlgorithm.handle(algorithm);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllAlgorithms() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAlgorithms.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllAlgorithms.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Algorithm>> getAllAlgorithms() {
    final String _sql = "SELECT * FROM algorithm ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"algorithm"}, false, new Callable<List<Algorithm>>() {
      @Override
      public List<Algorithm> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<Algorithm> _result = new ArrayList<Algorithm>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Algorithm _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new Algorithm(_tmpName,_tmpDescription);
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
  public Algorithm getAlgorithmById(final int algorithm_id) {
    final String _sql = "SELECT * FROM algorithm where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, algorithm_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final Algorithm _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result = new Algorithm(_tmpName,_tmpDescription);
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
  public Algorithm getAlgorithmByName(final String name) {
    final String _sql = "SELECT * FROM algorithm where name=?";
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
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final Algorithm _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result = new Algorithm(_tmpName,_tmpDescription);
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
