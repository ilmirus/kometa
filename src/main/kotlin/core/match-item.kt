package kometa

/**
 * Used internally in matchers in several situations:
 *  - As the result of a production, stores a range of input, and the results of the match.
 *  - As a parameter to a production, can hold an item of input, a range of input, or a production.
 */
class MatchItem<TInput, TResult> {
    private var inputSlice: Iterable<TInput>? = null

    /**
     * The entire input iterable that this item takes its input from.
     */
    var inputIterable: Iterable<TInput> = emptyList()
        set(value) {
            inputSlice = null
            field = value
        }

    private var inputStart: Int = -1
    private var inputNext: Int = -1

    private var id: String? = null

    /**
     * The production that this item is passing.
     */
    var production: Production<TInput, TResult>? = null
        private set

    /**
     * The name of the production that this item is passing.
     */
    var productionName: String? = null
        private set

    /**
     * The starting index in the match (not necessarily in the item's particular input).
     */
    var startIndex: Int = -1
        set(value) {
            field = value
            inputStart = value
        }

    /**
     * The next index in the match (not necessarily in the item's particular input).
     */
    var nextIndex: Int = -1
        set(value) {
            field = value
            inputNext = value
        }

    /**
     * Returns an iterable representing only this item's inputs (possibly a subset of inputIterable).
     */
    var inputs: Iterable<TInput>
        get() {
            if (inputSlice == null) {
                inputSlice = if (inputStart >= 0 && inputNext >= 0) {
                    inputIterable.toList().subList(inputStart, inputNext)
                } else {
                    emptyList()
                }
            }
            return inputSlice!!
        }
        set(value) {
            inputIterable = value
            inputSlice = value
        }

    /**
     * The results that this item holds.
     */
    var results: Iterable<TResult?> = emptyList()

    /**
     * Default constructor.
     */
    constructor()

    /**
     * Constructor.
     * @param input A single input to hold.
     */
    constructor(input: TInput) {
        inputIterable = listOf(input)
        inputSlice = inputIterable
        inputStart = 0
        inputNext = 1
    }

    /**
     * Constructor.
     *
     * @param input A single input to hold.
     * @param result The result of the input.
     */
    constructor(input: TInput, result: TResult) {
        inputIterable = listOf(input)
        inputSlice = inputIterable
        inputStart = 0
        inputNext = 1
        results = listOf(result)
    }

    /**
     * Constructor.
     *
     * @param inputs Inputs to hold.
     */
    constructor(inputs: Iterable<TInput>) {
        this.inputs = inputs
    }

    /**
     * Constructor.
     *
     * @param inputs Inputs to hold.
     * @param results The corresponding results.
     */
    constructor(inputs: Iterable<TInput>, results: Iterable<TResult>) {
        this.inputs = inputs
        this.results = results
    }

    /**
     * Constructor.
     *
     * @param start Start position in the match (not necessarily in the given inputs).
     * @param next Next position in the match (not necessarily in the given inputs).
     * @param inputs Input enumerable.
     * @param results Result enumerable.
     * @param relative Whether or not the start and next parameters are relative to the given input enumerable, or independent of it.
     */
    constructor(
        start: Int,
        next: Int,
        inputs: Iterable<TInput>,
        results: Iterable<TResult> = emptyList(),
        relative: Boolean = true
    ) {
        inputIterable = inputs
        startIndex = start
        nextIndex = next

        if (relative) {
            inputStart = start
            inputNext = next
        } else {
            inputStart = 0
            inputNext = inputs.toList().size
        }

        this.results = results
    }

    /**
     * Constructor.
     *
     * @param start Start position in the match.
     * @param inputs Inputs to hold.
     */
    constructor(start: Int, inputs: Iterable<TInput>) : this(start, start, inputs, emptyList(), true)

    /**
     * Constructor.
     *
     * @param start Start position in the match.
     * @param next Next position in the match.
     * @param input A single input to hold.
     * @param result A single result to hold.
     */
    constructor(start: Int, next: Int, input: TInput, result: TResult) {
        startIndex = start
        nextIndex = next
        inputIterable = listOf(input)
        inputSlice = inputIterable
        inputStart = 0
        inputNext = 1
        results = listOf(result)
    }

    /**
     * Constructor.
     *
     * @param p Production to pass.
     */
    constructor(production: Production<TInput, TResult>) {
        this.production = production
        productionName = production.methodName
    }

    /**
     * String representation.  This is used to memoize rules with variable arguments.
     */
    override fun toString(): String {
        if (id == null) {
            id = if (productionName != null) {
                "{{ $productionName }}"
            } else {
                val inputs = this.inputs.joinToString(separator = ",") { (it ?: "<null>").toString() }
                val results = this.results.joinToString(separator = ",") { (it ?: "<null>").toString() }
                "$startIndex-$nextIndex [$inputs] -> [$results]"
            }
        }
        return id!!
    }

    /**
     * Conversion to input type.
     */
    fun asInput(): TInput = inputs.last()

    /**
     * Conversion to result type.
     */
    fun asResult(): TResult? = results.lastOrNull()
}