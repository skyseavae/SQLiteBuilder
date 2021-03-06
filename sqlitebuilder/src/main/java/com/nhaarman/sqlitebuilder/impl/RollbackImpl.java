/*
 *  Copyright 2015 Niek Haarman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.Rollback;
import com.nhaarman.sqlitebuilder.RollbackTransaction;
import com.nhaarman.sqlitebuilder.SavePoint;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.To;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class RollbackImpl extends BaseFinishedStatement implements Rollback {

  @NotNull
  @Override
  public RollbackTransaction transaction() {
    return new RollbackTransactionImpl(this);
  }

  @NotNull
  @Override
  public To to() {
    return new ToImpl(this);
  }

  @NotNull
  @Override
  public SavePoint to(@NotNull final String savePointName) {
    return new SavePointImpl(savePointName, new ToImpl(this));
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend("ROLLBACK");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }
}
