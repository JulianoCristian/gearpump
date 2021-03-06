/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gearpump.streaming

import io.gearpump.streaming.dsl.api.functions.SerializableFunction
import io.gearpump.streaming.dsl.scalaapi.{Stream, StreamApp}
import io.gearpump.streaming.dsl.task.{GroupByTask, TransformTask}
import io.gearpump.streaming.sink.{DataSink, DataSinkTask}
import io.gearpump.streaming.source.{DataSource, DataSourceTask}


// scalastyle:off line.size.limit
/**
 *
 * The architecture of Gearpump Streaming DSL consists of several layers:
 *
 *   * User facing [[Stream]] DSL. Stream is created by [[StreamApp]]
 *     from input source like Kafka or by applying high level operations (e.g. flatMap, window, groupBy) to user defined functions(UDFs). UDFs are subclasses
 *     of [[SerializableFunction]], represented by [[io.gearpump.streaming.dsl.plan.Op]]
 *     in the underlying [[io.gearpump.util.Graph]].
 *   * [[io.gearpump.streaming.dsl.plan.Planner]], responsible for interpreting the Op Graph, optimizing it and building a low level Graph of
 *     [[io.gearpump.streaming.Processor]]. Finally, it creates a runnable Graph of [[io.gearpump.streaming.task.Task]].
 *   * The execution layer is usually composed of the following four tasks.
 *
 *     * [[DataSourceTask]] for [[DataSource]] to ingest data into Gearpump
 *     * [[DataSinkTask]] for [[DataSink]] to write data out.
 *     * [[GroupByTask]] to execute Ops followed by [[io.gearpump.streaming.dsl.plan.GroupByOp]]
 *     * [[TransformTask]] to execute all other Ops.
 *
 *     All but [[sink.DataSinkTask]] delegates execution to [[io.gearpump.streaming.dsl.window.impl.StreamingOperator]], which internally
 *     runs a chain of [[io.gearpump.streaming.dsl.plan.functions.FunctionRunner]] grouped by windows. Window assignments are either explicitly defined with
 *     [[io.gearpump.streaming.dsl.window.api.Windows]] API or implicitly in [[io.gearpump.streaming.dsl.window.api.GlobalWindows]]. UDFs are eventually
 *     executed by [[io.gearpump.streaming.dsl.plan.functions.FunctionRunner]].
 *
 */
// scalastyle:on line.size.limit
package object dsl {

}
