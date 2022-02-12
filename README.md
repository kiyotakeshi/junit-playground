- テストケースは、どのような条件でどのような操作をしたらどのような結果が期待値がされるかを記述したもの

- テストケースをまとめたものをテストスイートと呼ぶ

- ソフトウェアテストは品質保証になるだけでなく、設計そのものが妥当であるかを検証する目的として完成度を図る役割も持つ

- テスト技法はホワイトボックステストとブラックボックステストに分類できる
  - ホワイトボックステストは内部ロジックや内部仕様について考慮してテストケースを設計する
  - ブラックボックステストは内部仕様は考慮せず、外部仕様のみからテストケースを設計する(業務知識が重要)

- 同値クラスに対するテストは、ソフトウェアが同様の結果をもたらす値を「同値クラス」としてグループ化しテストデータを選択する

- 境界値に対するテストは、ソフトウェアが異なる結果をもたらす値を「境界値」として境界値の近辺からテストデータを選択する
  - 経験的にプログラムは仕様上の境界値付近で不具合を生み出しやすいという性質を利用

- ユニットテストはプログラムとして実行できる仕様書となる
  - 対象のクラスやメソッドの仕様を動くプログラムとして記述することにより、仕様を明確にし保証する

- ユニットテストは繰り返し実行できるため、リグレッション(修正と無関係の箇所が壊れること=デグレーション)の恐れを最小限にして新しい機能追加や改修を行える
  - 変更に強くなり品質の高いソフトウェアを作ることができる

- テスト失敗時に影響範囲と条件が絞り込みやすくなるため、テストケースは可能な限り小さな単位で多く作るべき(問題の局所化)

- 可読性の低い、前提条件、実行、検証が複雑に入り組んでいるテストコードはメンテナンス性が悪く、テストの質にも影響する

- 実行順序に依存しない独立したテストを作る
  - シングルトンのリソースやデータベースにアクセスするテストは気をつける
  - テストの終了処理でデータやリソースを前の状態に戻す

- テストケースにおける重要なポイント
  - テストを行う前提条件
  - テストに用いる入力値や操作
  - テストを行ったときに期待する値や動作

- テスト対象となるクラスやオブジェクトは SUT(System Under Test)と呼ばれる
  - 1つのテストケースでは1つのSUTを対象にする
  - テストコードではテスト対象クラスのインスタンスを sut という変数で扱うことで理解しやすくなる

- テストコードに含まれる変数名は実測値に actual を、期待値に expected を使用する

- ユニットテストのフェーズ
  - setup
    - SUTの初期化、必要な入力値、期待される結果を準備
  - exercise
    - 1度だけSUTに対してテストする操作を実行
  - verify
    - 実測値が期待値と等価であるかを比較検証(assertion = 表明)
  - tear down
    - 次のテストに影響がないように後始末
    
- テストフィクスチャ(text fixtures)はテスト実行時に必要とされるすべてのデータや状態のこと
  - テストを行うための入力値とテストの期待値で構成される
  - 生成が容易でないオブジェクトの場合、テストコードの見通しを良くするために、テストフィクスチャや外部定義ファイルに記述する
  - テストケースごとに独立し、テストの実行ごとに初期化され終了時に開放する戦略は、フィレッシュフィクスチャと呼ばれる

- 初期状態として妥当な値が設定されているかコンストラクタを検証するテストではインスタンスの生成が実行フェーズに相当

- パラメータ化テスト(parameterized test)で記述することで、入力値と期待値をテストケースとして独立して定義できる(=テストケースとテストデータの分離)

- カテゴリ化テスト(categorized test)で実行対象を絞り込むことでスローテスト問題に対応できる
  - カテゴリを特定するマーカーインターフェースを付与する

- テストしやすさをテスタビリティ(testability)と呼ぶ

- リファクタリングはプログラムの外部的な振る舞いを変えずに、内部構造を変更しソースコードを整理するテクニック
  - 本来はリファクタリングを行う前にユニットテストを作成することで外部的な振る舞いが変わらない保証を先に作る

- ユニットテストの独立性を高めたい場合は依存するオブジェクトをテストダブル(test double)に置き換える方法が有効
  - double は代役や影武者の意味
  - モックやスタブを使う

- スタブは依存するクラスやモジュールの代用として使用する仮のクラスやモジュール
  - **スタブは仮の実装で予測可能な値を返すことで、予測が難しいテストケースを検証可能なテストケースにするのが目的**

- モックは依存クラスやモジュールが正しく利用されているかを検証する目的で使われる
  - **目的のメソッドがテストの実行中に呼び出されたかを検証するのが目的**
  - 呼び出し回数の検証などを独自実装するのは複雑なのでライブラリ(Mockito など)を使うのが一般的

- モック、スタブは両方の目的で使用されることがあるため、曖昧にどちらも指す場合がある
  - スタブはテストコード以外でも仮実装として利用されることがある
  - ライブラリはモック機能がスタブ機能の上位互換となるため、モック用ライブラリとして公開されている

---

- テストを作成する際には、個々のメソッドをテストするとは思わず対象のクラスの振る舞いに注目する
  - ATMクラスに deposit(), withdraw(), getBalance() メソッドがある場合に作成するべきテスト(以下の名前は後に改める)
    - makeSingleWithdrawal(1回出金する)
    - makeSingleDeposit(1回の入金を行う)
    - makeMultipleDeposit(複数回の入金を行う)
    - attemptToWithdrawTooMuch(残高以上の出金を行う)
    - getBalance() をテストするだけなら、フィールドの値を返すだけなのでテストとして意味がない

- テスト対象コードとテストコードのパッケージ名を一致させ別ディレクトリに配置する maven の一般的な方法は同じパッケージに含まれることになる
  - テストクラスは対象のクラス内のアクセス修飾子なしのメンバーにアクセスできる

- private なふるまいをテストするべきか
  - private な実装の詳細をテストするのはテストが実装の詳細に踏み込みすぎていることがある
  - コードへの小さな変更が多数のテストの失敗を招き、テストコードの修正に追われてリファクタリングの意欲が下がる恐れも
  - private なふるまいをテストしたい設計は興味深いふるまいが多数埋もれていて SRP(Single Responsibility Principle)に反しているかもしれない
  - 興味深いふるまいがあれば別のクラスに移動させ public なメソッドとして利用できるようにしてテストする

- テストは1つの目的に特化したほうが良い
  - アサーションが失敗したときにどの振る舞いに問題があったかすぐわかる
  - すべてのテストケースが実行されることを保証できる

- 一般的で無意味なテスト名をつけない
  - 個々の振る舞いに着目したテストを作成するときちんとした名前を与えられる
    - makeSingleWithdrawal(1回出金する) -> withdrawalReducesBalanceByWithdrawnAmount(出金を行うとその分、残高が減る)
    - makeMultipleDeposit(複数回の入金を行う) -> withdrawalOfMoreThanAvailableFundsGeneratesError(残高以上の出金を行うとエラーが発生する)
    - attemptToWithdrawTooMuch(残高以上の出金を行う) -> multipleDepositsIncreaseBalanceBySumOfDeposits(複数回入金を行うとその合計金額分、残高が増加する)

- テストの名前は7語程度にするのが適切でそれより長いのは設計が間違っている可能性がある
  - doingSomeOperationGeneratesSomeResult(何らかの処理を行うと何らかの結果が発生する)
  - someResultOccursUnderSomeCondition(何らかの条件下では何らかの結果が発生する)
  
- Behavior駆動開発から派生した名付けパターンでは given-when-then で記述する
  - givenSomeContextWhenDoingSomeBehaviorThenSomeResultOccurs(何らかの条件下で、何らかのふるまいを行うと何らかの結果が発生する)
  - 少し長いので givenSomeContext を省略しても問題ない -> doingSomeOperationGeneratesSomeResult に近くなる

- 複数のテストが失敗している時に注目したいもの以外に @Ignore をつける
  - テストランナーから無視されていることが報告されるのでコメントアウトと違って忘れない

- FIRST
  - Fast
    - 低速なコンポーネントに依存しているコード量を減らす
  - Isolated
    - テストが失敗に終わる理由が複数考えられる場合はテストを分割する(SRP に従う)
  - Repeatable
    - 常に一定の時刻を表す java.time.Clock を渡して時々失敗するテストを作らないようにする
  - Self-validating(自律的検証)
    - テスト自身の中で検証も行う(ただ出力するだけにしない)
  - Timely
    - タイムリーにユニットテストを作成する
    - 小さなコードを記述してからテストを作成する、なれたらテストを先に作成する
    - 古いコードに対してテストを行おうとするよりトラブルを起こしている箇所や頻繁に変更される箇所にテストを書く

- テスト対象を選ぶための問いかけ Right-BICEP(右腕の力こぶ)
  - Right -> 結果は正しいか
    - 小さなコードで happy path をテストできないとしたら作ろうとしているものの理解がおぼつかない状態
    - 与えられたシナリオでどんな答えを返すべきかを示したテストをまず作成してその後でコードを記述するのが TDD スタイル
    - 不完全な要件があったらその時点で最善の選択に基づいてコードを記述し、都度改善する
  - Boundary -> 境界条件は適切か
  - Inverse -> 逆の関係はチェックできているか
  - Cross-check -> 別の方法を使ってチェックできているか
  - Error -> エラーの条件を強制的に発生させることはできるか
    - happy path じゃない発生するはずがないと思われるが実際に発生するエラーを強制的に発生させるテスト
  - Performance -> パフォーマンスの特性は許容範囲内か

- コードを記述する前にどのようなテストを行うかを考えるようにする
  - コードをひねり出してからテスト方法を考えるのではなく、作成しようとしているコードを表すテストを設計し、その後でコードを記述する

- 支柱エラー(fencepost error)のように実際の個数が想定していたものと1つ異なるというミスは頻繁に生まれる
  - 「0-1-Nルール」と呼ばれる3つの状態を押さえておけば網羅できる場合がある

- パフォーマンスが緊急の問題でないなら早まった最適化に時間を費やすべきではない
  - それよりもコードをクリーンな状態に保つよう努力するべき
  - 最適化されたコードは可読性が低く、保守のコストは増大し、柔軟性は低下する
