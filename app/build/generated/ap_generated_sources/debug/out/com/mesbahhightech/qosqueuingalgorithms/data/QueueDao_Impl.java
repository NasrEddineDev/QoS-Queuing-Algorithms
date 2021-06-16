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
public final class QueueDao_Impl implements QueueDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Queue> __insertionAdapterOfQueue;

  private final EntityDeletionOrUpdateAdapter<Queue> __deletionAdapterOfQueue;

  private final EntityDeletionOrUpdateAdapter<Queue> __updateAdapterOfQueue;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllQueues;

  public QueueDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQueue = new EntityInsertionAdapter<Queue>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `queue` (`id`,`name`,`content`,`example_id`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Queue value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        stmt.bindLong(4, value.getExample_id());
      }
    };
    this.__deletionAdapterOfQueue = new EntityDeletionOrUpdateAdapter<Queue>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `queue` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Queue value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfQueue = new EntityDeletionOrUpdateAdapter<Queue>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `queue` SET `id` = ?,`name` = ?,`content` = ?,`example_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Queue value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        stmt.bindLong(4, value.getExample_id());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllQueues = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM queue";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Queue queue) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfQueue.insert(queue);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Queue queue) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfQueue.handle(queue);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Queue queue) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfQueue.handle(queue);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllQueues() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllQueues.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllQueues.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Queue>> getAllQueues() {
    final String _sql = "SELECT * FROM queue ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"queue"}, false, new Callable<List<Queue>>() {
      @Override
      public List<Queue> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfExampleId = CursorUtil.getColumnIndexOrThrow(_cursor, "example_id");
          final List<Queue> _result = new ArrayList<Queue>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Queue _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final int _tmpExample_id;
            _tmpExample_id = _cursor.getInt(_cursorIndexOfExampleId);
            _item = new Queue(_tmpName,_tmpContent,_tmpExample_id);
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
  public Queue getQueueByExampleId(final int example_id) {
    final String _sql = "SELECT * FROM queue where example_id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, example_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfExampleId = CursorUtil.getColumnIndexOrThrow(_cursor, "example_id");
      final Queue _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpExample_id;
        _tmpExample_id = _cursor.getInt(_cursorIndexOfExampleId);
        _result = new Queue(_tmpName,_tmpContent,_tmpExample_id);
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
  public Queue getQueueById(final int queue_id) {
    final String _sql = "SELECT * FROM queue where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, queue_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfExampleId = CursorUtil.getColumnIndexOrThrow(_cursor, "example_id");
      final Queue _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpExample_id;
        _tmpExample_id = _cursor.getInt(_cursorIndexOfExampleId);
        _result = new Queue(_tmpName,_tmpContent,_tmpExample_id);
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
