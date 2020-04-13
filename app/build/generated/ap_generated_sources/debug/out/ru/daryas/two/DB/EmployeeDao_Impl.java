package ru.daryas.two.DB;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EmployeeDao_Impl implements EmployeeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Employee> __insertionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __deletionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __updateAdapterOfEmployee;

  public EmployeeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmployee = new EntityInsertionAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Employee` (`id`,`name`,`salary`,`img`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        stmt.bindLong(3, value.salary);
        final byte[] _tmp;
        _tmp = BitmapConverter.bitmapToBytes(value.img);
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindBlob(4, _tmp);
        }
      }
    };
    this.__deletionAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Employee` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Employee` SET `id` = ?,`name` = ?,`salary` = ?,`img` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        stmt.bindLong(3, value.salary);
        final byte[] _tmp;
        _tmp = BitmapConverter.bitmapToBytes(value.img);
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindBlob(4, _tmp);
        }
        stmt.bindLong(5, value.id);
      }
    };
  }

  @Override
  public void insert(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEmployee.insert(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final List<Employee> employee) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfEmployee.handleMultiple(employee);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Employee> getAll() {
    final String _sql = "SELECT * FROM employee";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
      final int _cursorIndexOfImg = CursorUtil.getColumnIndexOrThrow(_cursor, "img");
      final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Employee _item;
        _item = new Employee();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _item.salary = _cursor.getInt(_cursorIndexOfSalary);
        final byte[] _tmp;
        _tmp = _cursor.getBlob(_cursorIndexOfImg);
        _item.img = BitmapConverter.bytesToBitmap(_tmp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Employee getById(final int id) {
    final String _sql = "SELECT * FROM employee WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
      final int _cursorIndexOfImg = CursorUtil.getColumnIndexOrThrow(_cursor, "img");
      final Employee _result;
      if(_cursor.moveToFirst()) {
        _result = new Employee();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        _result.name = _cursor.getString(_cursorIndexOfName);
        _result.salary = _cursor.getInt(_cursorIndexOfSalary);
        final byte[] _tmp;
        _tmp = _cursor.getBlob(_cursorIndexOfImg);
        _result.img = BitmapConverter.bytesToBitmap(_tmp);
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
